package com.felix.net.bean

import android.os.Parcel
import android.os.Parcelable

/**
 * @Author: Mingfa.Huang
 * @Date: 2021/2/18
 * @Des: Contact
 */
data class Contact(
    val avator: String?,
    val id: String,
    val nickName: String?,
    val sex: Boolean?,
    val userId: String,
    val ext: ContactExt?,
    var isFriend: Boolean = false
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString(),
        source.readString() ?: "",
        source.readString(),
        source.readValue(Boolean::class.java.classLoader) as Boolean?,
        source.readString() ?: "",
        source.readParcelable<ContactExt>(ContactExt::class.java.classLoader),
        1 == source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(avator)
        writeString(id)
        writeString(nickName)
        writeValue(sex)
        writeString(userId)
        writeParcelable(ext, 0)
        writeInt((if (isFriend) 1 else 0))
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Contact> = object : Parcelable.Creator<Contact> {
            override fun createFromParcel(source: Parcel): Contact = Contact(source)
            override fun newArray(size: Int): Array<Contact?> = arrayOfNulls(size)
        }
    }
}

data class ContactExt(
    val remark: String?
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(remark)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<ContactExt> = object : Parcelable.Creator<ContactExt> {
            override fun createFromParcel(source: Parcel): ContactExt = ContactExt(source)
            override fun newArray(size: Int): Array<ContactExt?> = arrayOfNulls(size)
        }
    }
}