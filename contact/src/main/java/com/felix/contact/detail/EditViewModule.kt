package com.felix.contact.detail

import androidx.lifecycle.MutableLiveData
import com.felix.arch.mvvm.BaseViewModel
import com.felix.arch.mvvm.ResultBean
import com.felix.net.bean.Contact
import com.felix.net.data.DataDelegate

/**
 * @Author: Mingfa.Huang
 * @Date: 2021/2/20
 * @Des: EditViewModule
 */
class EditViewModule : BaseViewModel() {
    val contact: MutableLiveData<Contact> = MutableLiveData()
    fun updateRemark(remark: String) {
        if (remark == contact.value?.ext?.remark) {
            result.postValue(ResultBean(isSuccess = true))
        } else {
            //更新remark
            result.postValue(ResultBean(isSuccess = true))
        }
    }
}