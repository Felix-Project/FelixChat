package com.felix.net.bean

import android.os.Parcel
import android.os.Parcelable

/**
 * @Author: Mingfa.Huang
 * @Date: 2021/1/19
 * @Des: UserInfo
 */
data class UserInfo(
    var userId: String,
    var nickName: String?,
    var sex: Boolean?,
    var avator: String?
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString() ?: "",
        source.readString(),
        source.readValue(Boolean::class.java.classLoader) as Boolean?,
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(userId)
        writeString(nickName)
        writeValue(sex)
        writeString(avator)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<UserInfo> = object : Parcelable.Creator<UserInfo> {
            override fun createFromParcel(source: Parcel): UserInfo = UserInfo(source)
            override fun newArray(size: Int): Array<UserInfo?> = arrayOfNulls(size)
        }
    }
}