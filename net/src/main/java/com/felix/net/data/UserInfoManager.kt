package com.felix.net.data

import com.felix.mmkv.login.LoginSpDelegate
import com.felix.net.bean.UserInfo
import com.felix.utils.gson.fromJson
import com.felix.utils.gson.toJson

/**
 * @Author: Mingfa.Huang
 * @Date: 2021/1/19
 * @Des: UserInfoManager
 */
class UserInfoManager {
    companion object {
        val instance by lazy {
            UserInfoManager()
        }
    }

    var userInfo: UserInfo? = LoginSpDelegate.getLoginInfo()?.fromJson<UserInfo>()
        set(value) {
            field = value
            LoginSpDelegate.saveLoginInfo(value.toJson())
        }
}

inline val UserDelegate
    get() = UserInfoManager.instance.userInfo

inline val TokenDelegate
    get() = UserDelegate?.token
