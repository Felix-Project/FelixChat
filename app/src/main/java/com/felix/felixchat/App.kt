package com.felix.chat

import com.alibaba.android.arouter.launcher.ARouter
import com.felix.commgmt.BaseCompApp
import com.felix.mmkv.encryption.EncryptionSpDelegate

/**
 * @Author: Mingfa.Huang
 * @Date: 2021/1/19
 * @Des: App
 */
class App : BaseCompApp() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {           // These two lines must be written before init, otherwise these configurations will be invalid in the init process
            ARouter.openLog();     // Print log
            ARouter.openDebug();   // Turn on debugging mode (If you are running in InstantRun mode, you must turn on debug mode! Online version needs to be closed, otherwise there is a security risk)
        }
        ARouter.init(this); // As early as possible, it is recommended to initialize in the Application
        EncryptionSpDelegate.useEncryption(true)
    }
}