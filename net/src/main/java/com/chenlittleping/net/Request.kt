package com.chenlittleping.net

import com.google.gson.Gson
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.TimeUnit


/**
 * 网络请求工具
 *
 * @author Chen Xiaoping (562818444@qq.com)
 * @since AnotherOneKotlin
 * @version AnotherOneKotlin
 * @Datetime 2017-11-22 17:27
 *
 */
abstract class Request<T> {
    private val DEFAULT_TIME_OUT = 30L

    var service : T

    var queryMap = HashMap<String, String>()

    private lateinit var partMap : MutableMap<String, RequestBody>

    constructor(s : Class<T>) {
        var retrofit = Retrofit.Builder()
                .client(buildOkHttp())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(/*buildGson()*/))
                .baseUrl(getBaseUrl())
                .build()
        service = retrofit.create(s)
    }

    private fun buildOkHttp() : OkHttpClient {
        var client = OkHttpClient.Builder()
        client.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)  //连接超时时间
        client.writeTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)    //写操作 超时时间
        client.readTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)     //读操作超时时间

        var httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        client.addInterceptor(httpLoggingInterceptor)
        return client.build()
    }

    open fun buildGson() : Gson {
        return Gson()
    }

    fun buildQueryMap() : Map<String, String> {
        addDefData()
        return queryMap
    }

    fun addData(key : String, value : Any?) {
        when (value) {
            is File -> {
                if (partMap == null) {
                    partMap = ConcurrentHashMap()
                }
                val fileName = value.name
                val subtype = fileName.substring(fileName.lastIndexOf(".") + 1)
                val body = RequestBody.create(MediaType.parse("image/" + subtype), value)
                partMap.put(key + "\";filename=\"" + fileName, body)
            }
            is JSONObject -> {
                if (partMap == null) {
                    partMap = ConcurrentHashMap()
                }
                val body = RequestBody.create(MediaType.parse("application/json"), value.toString())
                partMap.put(key, body)
            }
            else -> {
                queryMap.put(key, value.toString())
            }
        }
    }

    protected abstract fun getBaseUrl() : String
    protected abstract fun addDefData()
}