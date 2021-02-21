package com.felix.encryption

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.felix.encryption.databinding.InputDialogBinding
import com.felix.lib_app_tools.toast.ToastDelegate
import com.felix.mmkv.encryption.EncryptionSpDelegate
import com.felix.utils.utils.encryption.decAes

/**
 * @Author: Mingfa.Huang
 * @Date: 2021/2/20
 * @Des: EncryptionUtils
 */
object EncryptionUtils {
    fun requireEncryption(context: Context, callback: (String?) -> Unit) {
        if (!EncryptionSpDelegate.getEncryption()) {
            callback.invoke(null)
            return
        }
        Dialog(context).apply {
            InputDialogBinding.inflate(LayoutInflater.from(context)).apply {
                setContentView(root)
                switchUseEncription.setOnCheckedChangeListener { buttonView, isChecked ->
                    groupPasswd.visibility =
                        takeIf { isChecked }?.let { View.VISIBLE } ?: View.GONE
                }
                val key = "e10adc3949ba59abbe56e057f20f883e"
                ("kdjoXoIBSj/ifeliwH+RUdWJ27oaXVlqmh+BQXm5KW8O6nChy0ZbaI0ssfvrSa9NwwAS4kZy" +
                        "JpaDA9czAZlW2XlA+tTrDuhXag/+NPNHV4dOv56jtN4OYjQyoZjnpq4meI8mZfSP9SC" +
                        "SeCnxkT+37hNvqc7nfJG4j8D966E/2rKvDst/qY8hPMuDRXNmPVOw+lGg3D9NNK9/" +
                        "3NA72QI1JA==").decAes(key).let {
                    tvTitle.text = it
                }
                "dq4s/Zex3WURyyPND20DF/CfePZ/htOJEMBWA6WeOJ8=".decAes(key).let {
                    switchUseEncription.text = it
                }

                "YlAfFXZPg0K+60Xi8JftcA==".decAes(key).let {
                    etPasswd1.hint = it
                }
                "T/RLA1BzGVIRJqIzgZZTaNpSzPcHaKd3/0VqKgu93yY=".decAes(key).let {
                    etPasswd2.hint = it
                }

                setOnDismissListener {
                    callback.invoke(null)
                }
                btnCancel.setOnClickListener {
                    dismiss()
                    callback.invoke(null)
                }
                btnConfirm.setOnClickListener {
                    if (switchUseEncription.isChecked) {
                        if (etPasswd1.text.isNullOrEmpty()) {
                            ToastDelegate.show(
                                "rC5WUeBP/UdcAHPw07dhFY14pVi9HCskoqIGcu6Tw3s=".decAes(
                                    key
                                )
                            )
                            return@setOnClickListener
                        }
                        if (etPasswd1.text.toString() != etPasswd2.text.toString()) {
                            ToastDelegate.show(
                                "PumWb2TeAa/FIO23MFIsIp70QTiAy7vM73Q8Chq2rcs=".decAes(
                                    key
                                )
                            )
                            return@setOnClickListener
                        }
                        callback.invoke(etPasswd1.text.toString())
                    } else {
                        dismiss()
                        callback.invoke(null)
                    }
                }

            }
        }.show()
    }
}