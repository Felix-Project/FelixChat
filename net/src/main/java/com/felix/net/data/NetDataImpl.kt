package com.felix.net.data

import com.felix.net.ApiDelegate
import com.felix.net.bean.*
import com.felix.net.bean.base.HttpResp
import com.felix.net.bean.base.isSuccess
import com.felix.net.bean.http.*
import com.felix.utils.utils.encryption.aes

/**
 * @Author: Mingfa.Huang
 * @Date: 2021/1/19
 * @Des: NetDataImpl
 */
class NetDataImpl : IData {
    override fun login(loginReqBean: LoginReqBean): HttpResp<UserInfo>? {
        return ApiDelegate.login(loginReqBean).let {
            it.execute().body()
        }
    }

    override fun getChatList(chatReq: ChatReq): List<List<ChatBean>> {
        return ApiDelegate.getChatList(chatReq).execute()?.body()?.obj?.let {
            val map = hashMapOf<String, MutableList<ChatBean>>()
            for (chatBean in it) {
                if (chatReq.userId.equals(chatBean.sendUser)) {
                    map.getOrPut(chatBean.receiveUser) {
                        mutableListOf()
                    }.add(chatBean)
                } else {
                    map.getOrPut(chatBean.sendUser) {
                        mutableListOf()
                    }.add(chatBean)
                }
            }
            map.values.map { it.toList() }.toList()
        } ?: emptyList()

    }

    override fun getUserInfo(userId: String): UserInfo? {
        return UserInfoReq(specifyUserId = userId).run {
            ApiDelegate.getUserInfo(this)?.execute()?.body()?.obj?.firstOrNull()
        }
    }

    override fun getFriendList(userId: String): List<UserInfo>? {
        return UserInfoReq(ownerUserId = userId).run {
            ApiDelegate.getUserInfo(this)?.execute()?.body()?.obj
        }
    }

    override fun getContactList(contactReq: ContactReq): List<Contact>? {
        return null
    }

    override fun addContact(addContactReq: AddContactReq, key: String): HttpResp<Any>? {
        return addContactReq.also {
            if (it.encrypted) {
                it.remark = it.remark.aes(key) ?: ""
                it.level = it.level.aes(key) ?: ""
                it.remark = it.remark.aes(key) ?: ""
                it.groupName = it.groupName.aes(key) ?: ""
            }
        }.let {
            ApiDelegate.addContact(it)?.execute()?.body()
        }
    }

    override fun searchContact(searchReq: SearchReq): HttpResp<List<Contact>>? {
        return ApiDelegate.searchContact(searchReq).execute()?.body()
    }
}