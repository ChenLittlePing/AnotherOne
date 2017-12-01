package com.chenlittleping.net


/**
 * FIXME
 *
 * @author Chen Xiaoping (562818444@qq.com)
 * @since AnotherOneKotlin
 * @version AnotherOneKotlin
 * @Datetime 2017-11-22 22:40
 *
 */
class INetOut<R> : IOut <R> {
    private var onSuccess : ((r : R?) -> Unit)? = null
    private var onError : ((info : String) -> Unit)? = null

    override fun success(r: R?) {
        onSuccess?.invoke(r)
    }

    override fun error(info: String) {
        onError?.invoke(info)
    }

    fun onSuccess(l : (r : R?) -> Unit) {
        onSuccess = l
    }

    fun onError(l : (r : String) -> Unit) {
        onError = l
    }
}