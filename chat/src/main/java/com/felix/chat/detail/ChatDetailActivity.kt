package com.felix.chat.detail

import android.os.Bundle
import com.felix.arch.mvvm.BaseMvvmActivity
import com.felix.chat.databinding.ActivityChatDetailBinding

class ChatDetailActivity : BaseMvvmActivity<ChatDetailViewModule>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityChatDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}