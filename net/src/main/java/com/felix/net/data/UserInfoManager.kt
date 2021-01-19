package com.felix.net.data

import com.felix.mmkv.login.LoginSpDelegate
import com.felix.net.bean.LoginResp
import com.felix.utils.gson.fromJson

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

    var loginResp: LoginResp? = LoginSpDelegate.getLoginInfo()?.fromJson<LoginResp>()
}

inline val UserDelegate
    get() = UserInfoManager.instance.loginResp

inline val TokenDelegate
    get() = UserDelegate?.token

inline val UserInfoDelegate
    get() = UserDelegate?.userInfo