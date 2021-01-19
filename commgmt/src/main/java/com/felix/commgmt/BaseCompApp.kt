package com.felix.commgmt

import android.app.Application
import android.content.Context
import com.felix.combase.IAppInit
import com.felix.mmkv.MmkvAppInit
import com.felix.utils.AppUtils

/**
 * @Author: Mingfa.Huang
 * @Date: 2020/12/5
 * @Des: BaseCompApp
 */
open class BaseCompApp : Application(), IAppInit {
    val appModule: Array<IAppInit> = arrayOf(MmkvAppInit())
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        AppUtils.setup(this)
    }

    override fun onCreate() {
        super.onCreate()
        initModule(this)
    }

    override fun initModule(app: Application) {
        for (iAppInit in appModule) {
            iAppInit.initModule(app)
        }
    }
}