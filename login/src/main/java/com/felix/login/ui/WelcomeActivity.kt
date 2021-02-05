package com.felix.login.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.felix.login.R
import com.felix.net.data.TokenDelegate

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        TokenDelegate?.also {
            ARouter.getInstance().build("/app/MainActivity").navigation()
        } ?: kotlin.run {
            ARouter.getInstance().build("/login/LoginActivity").navigation()
        }
        finish()
    }
}