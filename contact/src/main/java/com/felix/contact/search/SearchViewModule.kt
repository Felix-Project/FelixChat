package com.felix.contact.search

import com.felix.arch.mvvm.BaseViewModel
import com.felix.arch.mvvm.ListLiveData
import com.felix.lib_app_tools.toast.ToastDelegate
import com.felix.net.bean.Contact
import com.felix.net.bean.base.isSuccess
import com.felix.net.bean.http.SearchReq
import com.felix.net.data.DataDelegate
import com.felix.net.data.UserDelegate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * @Author: Mingfa.Huang
 * @Date: 2021/2/20
 * @Des: SearchViewModule
 */
class SearchViewModule : BaseViewModel() {
    val contactList = ListLiveData<Contact>()

    fun getSearch(keyword: String) {
        scope.launch(Dispatchers.IO) {
            SearchReq(
                majorUser = UserDelegate?.id ?: "",
                keyword = keyword
            ).runCatching {
                DataDelegate.searchContact(this)
            }.also {
                it.exceptionOrNull()?.let {
                    ToastDelegate.show("网络异常")
                    it.printStackTrace()
                }
            }.getOrNull()?.let {
                if (it.isSuccess()) {
                    contactList.value?.clear()
                    contactList.addValue(it.obj ?: emptyList())
                } else {
                    ToastDelegate.show(it.msg)
                }

            }
        }
    }
}