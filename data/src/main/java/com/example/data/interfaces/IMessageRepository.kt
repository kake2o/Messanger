package com.example.data.interfaces

import com.example.data.models.Message

interface IMessageRepository {
    fun listenForMessages(channelID: String, onMessagesUpdated: (List<Message>) -> Unit)
    fun sendMessage(channelID: String, messageText: String)
}