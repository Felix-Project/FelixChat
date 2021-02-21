package com.felix.chat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.felix.chat.databinding.ActivityMainBinding
import com.felix.felixchat.MainPager
import com.google.android.material.tabs.TabLayoutMediator

@Route(path = "/app/MainActivity")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            val titles = arrayOf("聊天", "通讯录", "发现", "我")
            ivAddContact.setOnClickListener {
                ARouter.getInstance().build("/contact/SearchActivity").navigation()
            }
            vpMain.adapter = MainPager(supportFragmentManager, lifecycle)
            TabLayoutMediator(tabMain, vpMain) { tab, position ->
                tab.text = titles[position]

            }.attach()
        }
    }
}