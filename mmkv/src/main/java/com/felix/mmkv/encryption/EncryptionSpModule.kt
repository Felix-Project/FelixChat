package com.felix.mmkv.encryption

import com.felix.utils.gson.fromJson
import com.felix.utils.gson.toJson
import com.tencent.mmkv.MMKV
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

/**
 * @Author: Mingfa.Huang
 * @Date: 2021/1/19
 * @Des: LoginModule
 */
internal class EncryptionSpModule : IEncryptionSp {
    companion object {
        const val SpId = "EncryptionSpModule"
        private const val KEY_USE_ENCRYPTION = "USE_ENCRYPTION"
        private const val KEY_CHAT_HOLDER = "CHAT_HOLDER"
        val instance by lazy {
            EncryptionSpModule()
        }
    }

    private val mmkv = MMKV.mmkvWithID(SpId)

    override fun useEncryption(enable: Boolean) {
        mmkv?.encode(KEY_USE_ENCRYPTION, enable)
    }

    override fun getEncryption(): Boolean {
        return mmkv?.decodeBool(KEY_USE_ENCRYPTION) ?: false
    }

    override fun saveChatKey(holder: IEncryptionSp.EncryptionKeyHolder) {
        clearChatKey()
        if (holder.timeType == EncryptionTimeType.EXPIRE_TIME) {
            mmkv?.encode(KEY_CHAT_HOLDER.plus(holder.id), holder.toJson())
        }
    }

    override fun getChatKey(id: String): IEncryptionSp.EncryptionKeyHolder? {
        return mmkv?.decodeString(KEY_CHAT_HOLDER.plus(id))?.let {
            it.fromJson<IEncryptionSp.EncryptionKeyHolder>()
        }?.let {
            if (it.expireTime > System.currentTimeMillis()) {
                it
            } else {
                clearChatKey()
                null
            }
        }
    }

    private var lastTime: Long = 0
    private fun clearChatKey() {
        if ((System.currentTimeMillis() - lastTime) < 300_000) {
            //5分钟清理一次
            return
        }
        GlobalScope.launch(Dispatchers.IO) {
            mmkv?.allKeys()?.filterNotNull()?.filter {
                it.startsWith(KEY_CHAT_HOLDER)
            }?.forEach { key ->
                kotlin.runCatching {
                    mmkv?.decodeString(key)?.fromJson<IEncryptionSp.EncryptionKeyHolder>()?.let {
                        if (it.expireTime <= System.currentTimeMillis()) {
                            mmkv?.remove(key)
                        }
                    }
                }
            }
        }
    }
}