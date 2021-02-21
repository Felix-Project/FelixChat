package com.felix.contact.detail

import androidx.lifecycle.MutableLiveData
import com.felix.arch.mvvm.BaseViewModel
import com.felix.lib_app_tools.toast.ToastDelegate
import com.felix.net.bean.Contact
import com.felix.net.bean.base.isSuccess
import com.felix.net.bean.http.AddContactReq
import com.felix.net.data.DataDelegate
import com.felix.net.data.UserDelegate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * @Author: Mingfa.Huang
 * @Date: 2021/2/20
 * @Des: ContactDetailViewModule
 */
class ContactDetailViewModule : BaseViewModel() {
    val contact = MutableLiveData<Contact>()

    fun addContact(key: String?) {
        scope.launch(Dispatchers.IO) {
            contact.value?.let { contact ->
                UserDelegate?.id?.let {
                    AddContactReq(
                        majorUser = it,
                        friendUser = contact.id
                    )
                }
            }?.apply {
                this.encrypted = !key.isNullOrEmpty()
            }.runCatching {
                this?.let {
                    DataDelegate.addContact(it, key ?: "")
                }
            }.also {
                it.exceptionOrNull()?.let {
                    ToastDelegate.show(it.message)
                    it.printStackTrace()
                }
            }.getOrNull()?.let {
                if (it.isSuccess()) {
                    ToastDelegate.show("发送成功")
                }
            }
        }
    }
}