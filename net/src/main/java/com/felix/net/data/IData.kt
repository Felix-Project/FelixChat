package com.felix.net.data

import com.felix.net.bean.*
import com.felix.net.bean.base.HttpResp
import com.felix.net.bean.http.*

/**
 * @Author: Mingfa.Huang
 * @Date: 2021/1/19
 * @Des: IData
 */
interface IData {
    fun login(loginReqBean: LoginReqBean): HttpResp<UserInfo>?

    fun getChatList(chatReq: ChatReq): List<List<ChatBean>>

    fun getUserInfo(userId: String): UserInfo?

    fun getFriendList(userId: String): List<UserInfo>?

    fun getContactList(contactReq: ContactReq): List<Contact>?

    fun addContact(addContactReq: AddContactReq, key: String): HttpResp<Any>?

    fun searchContact(searchReq: SearchReq): HttpResp<List<Contact>>?
}