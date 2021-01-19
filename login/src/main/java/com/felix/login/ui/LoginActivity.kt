package com.felix.login.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.widget.addTextChangedListener
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.felix.arch.mvvm.BaseActivity
import com.felix.lib_app_tools.toast.ToastDelegate
import com.felix.login.databinding.ActivityLoginBinding
import com.felix.net.bean.LoginReqBean
import com.felix.net.data.DataDelegate
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

@Route(path = "/login/LoginActivity")
class LoginActivity : BaseActivity() {
    private val scope by lazy { MainScope() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            username.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun afterTextChanged(p0: Editable?) {
                    if (p0.isNullOrBlank()) {
                        login.isEnabled = false
                    } else {
                        login.isEnabled = password.text.isNotEmpty()
                    }
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }
            })
            password.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun afterTextChanged(p0: Editable?) {
                    if (p0.isNullOrBlank()) {
                        login.isEnabled = false
                    } else {
                        login.isEnabled = username.text.isNotEmpty()
                    }
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }
            })
            login.setOnClickListener {
                if (username.text.isNullOrBlank()) {
                    ToastDelegate.show("用户名不能为空")
                    return@setOnClickListener
                }
                if (password.text.isNullOrBlank()) {
                    ToastDelegate.show("密码不能为空")
                    return@setOnClickListener
                }
                scope.launch {
                    LoginReqBean(
                        userId = username.text.toString(),
                        passwd = password.text.toString()
                    ).runCatching {
                        DataDelegate.login(this)
                    }.also {
                        it.exceptionOrNull()?.printStackTrace()
                    }.getOrNull()?.let {
                        ARouter.getInstance().build("/app/MainActivity").navigation()
                    } ?: kotlin.run {
                        ToastDelegate.show("登录失败")
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        scope?.cancel()
    }
}