package com.felix.mmkv

import android.app.Application
import com.felix.combase.IAppInit
import com.tencent.mmkv.MMKV

/**
 * @Author: Mingfa.Huang
 * @Date: 2021/1/19
 * @Des: MmkvAppInit
 */
class MmkvAppInit : IAppInit {
    override fun initModule(app: Application) {
        AppDelegate=app
        MMKV.initialize(app)
    }
}
internal lateinit var AppDelegate: Application