package com.felix.mmkv.encryption

import com.felix.mmkv.login.ILoginSp
import com.felix.mmkv.login.LoginSpModule

/**
 * @Author: Mingfa.Huang
 * @Date: 2021/1/19
 * @Des: ILoginSp
 */
interface IEncryptionSp {
    data class EncryptionKeyHolder(
        var key: String,
        var id: String,
        var timeType: EncryptionTimeType = EncryptionTimeType.EVERY_TIME,
        var expireTime: Long = 0
    )

    fun useEncryption(enable: Boolean)
    fun getEncryption(): Boolean

    fun saveChatKey(holder: EncryptionKeyHolder)
    fun getChatKey(id: String): EncryptionKeyHolder?
}

enum class EncryptionTimeType {
    EVERY_TIME, REENTER_PAGE, RESTART_APP, EXPIRE_TIME
}

val EncryptionSpDelegate: IEncryptionSp
    get() = EncryptionSpModule.instance