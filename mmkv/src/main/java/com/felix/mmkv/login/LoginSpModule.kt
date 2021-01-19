package com.felix.mmkv.login

import com.tencent.mmkv.MMKV

/**
 * @Author: Mingfa.Huang
 * @Date: 2021/1/19
 * @Des: LoginModule
 */
internal class LoginSpModule : ILoginSp {
    companion object {
        const val SpId = "LoginSpModule"
        private const val KEY_LOGIN_INFO = "LOGIN_INFO"
        val instance by lazy {
            LoginSpModule()
        }
    }

    private val mmkv = MMKV.mmkvWithID(SpId)

    override fun getLoginInfo(): String? {
        return mmkv?.decodeString(KEY_LOGIN_INFO)

    }

    override fun saveLoginInfo(info: String) {
        mmkv?.encode(KEY_LOGIN_INFO, info)
    }
}