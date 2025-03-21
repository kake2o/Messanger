package com.example.almosttinder.presentation.chat


import androidx.lifecycle.ViewModel
import com.example.data.models.Message
import com.example.data.repository.MessagesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val messagesRepository: MessagesRepository
) : ViewModel() {

    private val _messages = MutableStateFlow<List<Message>>(emptyList())
    val messages = _messages.asStateFlow()


    fun listenForMessages(channelID: String) {
        messagesRepository.listenForMessages(channelID = channelID) { updatedMessage ->
            _messages.update { updatedMessage }
        }
    }

    fun sendMessage(channelID: String, messageText: String) {
        messagesRepository.sendMessage(channelID, messageText)
    }
}