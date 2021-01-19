package com.felix.net.bean.base

import com.felix.log.LogUtil
import com.felix.utils.utils.ITAG

/**
 * @Author: Mingfa.Huang
 * @Date: 2021/1/19
 * @Des: HttpReq
 */
data class HttpResp<T>(var code: Int, var msg: String?, var obj: T?) : ITAG

inline fun <T> HttpResp<T>.isSuccess(): Boolean {
    return (this.code and HTTP_TYPE_MASK) == HTTP_TYPE_SUCCESS
}

inline fun <T> HttpResp<T>.getObj(): T? {
    if (isSuccess()) {
        return obj
    }
    LogUtil.w(TAG, "code=$code,msg=$msg")
    return null
}


const val HTTP_TYPE_MASK = 0xFFFF0000.toInt()
const val HTTP_CODE_MASK = 0x0000FFFF

const val HTTP_TYPE_SUCCESS = 0x00010000
const val HTTP_TYPE_TOKEN = 0x00020000
const val HTTP_TYPE_SERVICE = 0x00030000