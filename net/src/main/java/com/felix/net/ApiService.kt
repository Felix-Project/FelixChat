package com.felix.net

import com.felix.net.bean.*
import com.felix.net.bean.base.HttpResp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * @Author: Mingfa.Huang
 * @Date: 2021/1/19
 * @Des: ApiService
 */
internal interface ApiService {

    @POST("chat/user/login.php")
    fun login(@Body loginReqBean: LoginReqBean): Call<HttpResp<UserInfo>>

    fun getChatList(@Body chatReq: ChatReq): Call<HttpResp<List<ChatBean>>>

    fun getUserInfo(@Body userInfoReq: UserInfoReq): Call<HttpResp<List<UserInfo>>>


    companion object {
        const val BASE_URL="http://192.168.1.221:8080"
//        const val BASE_URL="http://jp-tyo-dvm-2.sakurafrp.com:65076"
        fun create(): ApiService {
            val logger = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }

}

internal inline val ApiDelegate
    get() = ApiService.create()