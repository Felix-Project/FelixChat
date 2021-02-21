package com.felix.contact.search

import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import com.alibaba.android.arouter.facade.annotation.Route
import com.felix.arch.mvvm.BaseMvvmActivity
import com.felix.contact.databinding.ActivitySearchBinding
import com.felix.contact.detail.ContactDetailActivity

@Route(path = "/contact/SearchActivity")
class SearchActivity : BaseMvvmActivity<SearchViewModule>() {
    lateinit var binding: ActivitySearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            etKeyword.setOnEditorActionListener { v, actionId, event ->
                Log.i(TAG, "onActivityCreated: ")
                arrayOf(
                    EditorInfo.IME_ACTION_SEARCH
                    , EditorInfo.IME_ACTION_DONE
//                    , EditorInfo.IME_ACTION_UNSPECIFIED
                ).takeIf { actionId in it }?.let {
                    showLoading("正在加载")
                    viewModel.getSearch(etKeyword.text.toString())
                }
                true
            }
            etKeyword.requestFocus()
        }
        //view model observe
        observe(viewModel.contactList) {
            dismissLoading()
            if (it.isEmpty()) {
                showInfo("用户不存在")
            } else {
                it.first()?.let {
                    ContactDetailActivity.startContactDetail(this, it)
                }
            }
        }
    }
}