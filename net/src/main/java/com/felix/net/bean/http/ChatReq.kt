package com.felix.net.bean.http

/**
 * @Author: Mingfa.Huang
 * @Date: 2021/1/19
 * @Des: ChatReq
 */
data class ChatReq(
    var minMsgId: String?,
    var maxMsgId: String?,
    var userId: String,
    var chatUserId: String
)