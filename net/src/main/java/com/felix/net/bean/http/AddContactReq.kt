package com.felix.net.bean.http

/**
 * @Author: Mingfa.Huang
 * @Date: 2021/2/20
 * @Des: AddContactReq
 */
data class AddContactReq(
    var majorUser: String,
    var friendUser: String,
    var remark: String = "",
    var level: String = "0",
    var groupName: String = "",
    var agree: Boolean = false,
    var encrypted: Boolean = true
)