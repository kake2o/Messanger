package com.tinder.domain.repository

import com.tinder.domain.models.Message

interface MessageRepository {
    fun listenForMessages(channelID: String, onMessagesUpdated: (List<Message>) -> Unit)
    fun sendMessage(channelID: String, messageText: String)
}