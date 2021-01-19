package com.felix.net.data

import com.felix.net.ApiDelegate
import com.felix.net.bean.*
import com.felix.net.bean.base.getObj

/**
 * @Author: Mingfa.Huang
 * @Date: 2021/1/19
 * @Des: NetDataImpl
 */
class NetDataImpl : IData {
    override fun login(loginReqBean: LoginReqBean): LoginResp? {
        return ApiDelegate.login(loginReqBean).let {
            it.execute().body()?.getObj()
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
}