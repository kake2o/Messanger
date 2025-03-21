package com.tinder.domain.usecase

import com.tinder.domain.repository.MessageRepository

class SendMessageUseCase(private val messageRepository: MessageRepository) {
    suspend fun execute(channelId: String, message: String) {
        return messageRepository.sendMessage(channelID = channelId, messageText = message)
    }
}