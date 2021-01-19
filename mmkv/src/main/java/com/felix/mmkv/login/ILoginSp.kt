package com.felix.mmkv.login

/**
 * @Author: Mingfa.Huang
 * @Date: 2021/1/19
 * @Des: ILoginSp
 */
interface ILoginSp {
    fun saveLoginInfo(info: String)
    fun getLoginInfo(): String?
}

val LoginSpDegelate: ILoginSp
    get() = LoginSpModule.instance