package com.felix.net.bean

import android.os.Parcel
import android.os.Parcelable

/**
 * @Author: Mingfa.Huang
 * @Date: 2021/1/19
 * @Des: UserInfo
 */
data class UserInfo(
    val avator: String?,
    val id: String,
    val nickName: String?,
    val sex: Boolean?,
    val token: String,
    val userId: String
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString(),
        source.readString() ?: "",
        source.readString(),
        source.readValue(Boolean::class.java.classLoader) as Boolean?,
        source.readString() ?: "",
        source.readString() ?: ""
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(avator)
        writeString(id)
        writeString(nickName)
        writeValue(sex)
        writeString(token)
        writeString(userId)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<UserInfo> = object : Parcelable.Creator<UserInfo> {
            override fun createFromParcel(source: Parcel): UserInfo = UserInfo(source)
            override fun newArray(size: Int): Array<UserInfo?> = arrayOfNulls(size)
        }
    }
}