package com.felix.contact.detail

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.bumptech.glide.Glide
import com.felix.arch.mvvm.BaseMvvmActivity
import com.felix.contact.databinding.ActivityContactDetailBinding
import com.felix.encryption.EncryptionUtils
import com.felix.net.BASE_URL
import com.felix.net.bean.Contact

@Route(path = "/contact/ContactDetailActivity")
class ContactDetailActivity : BaseMvvmActivity<ContactDetailViewModule>() {
    lateinit var binding: ActivityContactDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent.getParcelableExtra<Contact>(KEY_CONTACT)?.let {
            viewModel.contact.postValue(it)
        } ?: kotlin.run {
            //数据异常
            finish()
        }
        binding = ActivityContactDetailBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }

        observe(viewModel.contact) { contact ->
            with(binding) {
                Glide.with(ivAvator).load(BASE_URL.plus(contact.avator)).into(ivAvator)
                tvNickName.text = contact.ext?.remark ?: contact.nickName
                tvEditRemark.isEnabled = contact.isFriend
                tvEditRemark.setOnClickListener {
                    EditContactActivity.startEdit(
                        this@ContactDetailActivity,
                        contact,
                        EDIT_CONTACT_CODE
                    )
                }
                btnAddContact.isEnabled = !contact.isFriend
                btnAddContact.setOnClickListener {
                    EncryptionUtils.requireEncryption(this@ContactDetailActivity) {
                        viewModel.addContact(it)
                    }
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == EDIT_CONTACT_CODE && resultCode == Activity.RESULT_OK) {
            data?.getParcelableExtra<Contact>(EditContactActivity.KEY_CONTACT)?.let {
                viewModel.contact.postValue(it)
            }

        }
    }

    companion object {
        private const val EDIT_CONTACT_CODE = 100
        private const val KEY_CONTACT = "ContactDetailActivity_CONTACT"

        /**
         * @param context should be a activity or a fragment
         */
        fun startContactDetail(context: Context, contact: Contact) {
            Intent(context, ContactDetailActivity::class.java).run {
                putExtra(KEY_CONTACT, contact)
                context.startActivity(this)
            }
        }
    }
}