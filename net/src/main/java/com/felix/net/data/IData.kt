package com.felix.net.data

import com.felix.net.bean.*

/**
 * @Author: Mingfa.Huang
 * @Date: 2021/1/19
 * @Des: IData
 */
interface IData {
    fun login(loginReqBean: LoginReqBean): LoginResp?

    fun getChatList(chatReq: ChatReq): List<List<ChatBean>>

    fun getUserInfo(userId: String): UserInfo?

    fun getFriendList(userId: String): List<UserInfo>?
}