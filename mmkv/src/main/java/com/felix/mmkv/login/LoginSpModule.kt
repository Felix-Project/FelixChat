package com.felix.mmkv.login

import com.tencent.mmkv.MMKV

/**
 * @Author: Mingfa.Huang
 * @Date: 2021/1/19
 * @Des: LoginModule
 */
internal class LoginSpModule : ILoginSp {
    companion object {
        val instance by lazy {
            LoginSpModule()
        }
    }

    init {
        MMKV.defaultMMKV()
    }

    override fun getLoginInfo(): String? {
        return ""
    }

    override fun saveLoginInfo(info: String) {

    }
}