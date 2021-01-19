package com.felix.net.bean

/**
 * @Author: Mingfa.Huang
 * @Date: 2021/1/19
 * @Des: ChatBean
 */
data class ChatBean(
    var chatId: String,
    var sendUser: String,
    var receiveUser: String,
    var chatTYpe: Short,
    var chatLink: String?,
    var sendTime: Long
)