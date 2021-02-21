package com.felix.net.data

import okhttp3.Interceptor
import okhttp3.Response

/**
 * @Author: Mingfa.Huang
 * @Date: 2021/2/18
 * @Des: TokenIntercept
 */
class TokenIntercept : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().addHeader("token", TokenDelegate)
            .addHeader("id", UserDelegate?.id).build()
        return chain.proceed(request)
    }
}