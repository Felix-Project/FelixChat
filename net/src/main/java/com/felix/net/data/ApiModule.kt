package com.felix.net.data

/**
 * @Author: Mingfa.Huang
 * @Date: 2021/1/19
 * @Des: ApiModule
 */
class ApiModule : IData by NetDataImpl() {
    companion object {
        val instance by lazy { ApiModule() }
    }
}

inline val DataDelegate: IData
    get() = ApiModule.instance