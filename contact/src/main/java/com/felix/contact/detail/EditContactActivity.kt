package com.felix.contact.detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import com.felix.arch.mvvm.BaseMvvmActivity
import com.felix.contact.databinding.ActivityEditContactBinding
import com.felix.lib_app_tools.toast.ToastDelegate
import com.felix.net.bean.Contact

class EditContactActivity : BaseMvvmActivity<EditViewModule>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent.getParcelableExtra<Contact>(KEY_CONTACT)?.let {
            viewModel.contact.postValue(it)
        } ?: kotlin.run {
            //数据异常
            finish()
        }
        ActivityEditContactBinding.inflate(layoutInflater).apply {
            setContentView(root)
            etRemark.setOnEditorActionListener { v, actionId, event ->
                Log.i(TAG, "onActivityCreated: ")
                arrayOf(
                    EditorInfo.IME_ACTION_SEARCH
                    , EditorInfo.IME_ACTION_DONE
//                    , EditorInfo.IME_ACTION_UNSPECIFIED
                ).takeIf { actionId in it }?.let {
                    confirmRemark(etRemark.text.toString())
                }
                true
            }

        }
        observe(viewModel.result) {
            dismissLoading()
            if (it.isSuccess) {
                intent.run {
                    putExtra(KEY_CONTACT, viewModel.contact.value)
                    setResult(Activity.RESULT_OK, intent)
                }
            } else {
                ToastDelegate.show("修改失败")
            }
            finish()

        }
    }

    private fun confirmRemark(remark: String) {
        showLoading("正在修改备注")
        viewModel.updateRemark(remark)
    }

    companion object {
        const val KEY_CONTACT = "CONTACT"

        /**
         * @param obj should be a activity or a fragment
         */
        fun startEdit(obj: Any, contact: Contact, requestCode: Int) {
            val context = obj.let {
                if (obj is Activity) {
                    obj
                } else if (obj is Fragment) {
                    obj.context
                } else {
                    null
                }
            }
            context?.let {
                Intent(it, EditContactActivity::class.java)
            }?.run {
                putExtra(KEY_CONTACT, contact)
                if (obj is Activity) {
                    obj.startActivityForResult(this, requestCode)
                } else if (obj is Fragment) {
                    obj.startActivityForResult(this, requestCode)
                }
            } ?: kotlin.run {
                val s = "obj should be a activity or androidx.fragment.app.fragment"
                Log.e("EditContactActivity", "startEdit: ", IllegalArgumentException(s))
            }
        }
    }
}