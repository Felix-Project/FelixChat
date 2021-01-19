package com.felix.net

import com.felix.net.bean.*
import com.felix.net.bean.base.HttpResp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body

/**
 * @Author: Mingfa.Huang
 * @Date: 2021/1/19
 * @Des: ApiService
 */
interface ApiService {

    fun login(@Body loginReqBean: LoginReqBean): Call<HttpResp<LoginResp>>

    fun getChatList(@Body chatReq: ChatReq): Call<HttpResp<List<ChatBean>>>

    fun getUserInfo(@Body userInfoReq: UserInfoReq): Call<HttpResp<List<UserInfo>>>


    companion object {
        fun create(): ApiService {
            val logger = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl("BASE_URL")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }

}

inline val ApiDelegate
    get() = ApiService.create()