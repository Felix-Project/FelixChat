package com.felix.log

import android.util.Log

/**
 * @Author: Mingfa.Huang
 * @Date: 2021/1/19
 * @Des: LogUtil
 */
object LogUtil {
    fun i(tag: String?, msg: String?, throwable: Throwable? = null) {
        Log.i(tag, msg, throwable)
    }

    fun d(tag: String?, msg: String?, throwable: Throwable? = null) {
        Log.d(tag, msg, throwable)
    }

    fun w(tag: String?, msg: String?, throwable: Throwable? = null) {
        Log.w(tag, msg, throwable)
    }

    fun v(tag: String?, msg: String?, throwable: Throwable? = null) {
        Log.v(tag, msg, throwable)
    }

    fun e(tag: String?, msg: String?, throwable: Throwable? = null) {
        Log.e(tag, msg, throwable)
    }
}