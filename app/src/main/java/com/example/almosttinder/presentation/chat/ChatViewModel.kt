package com.example.almosttinder.presentation.chat


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.MessagesRepository
import com.tinder.domain.models.Message
import com.tinder.domain.usecase.SendMessageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val messagesRepository: MessagesRepository,
    private val sendMessageUseCase: SendMessageUseCase
) : ViewModel() {

    private val _messages = MutableStateFlow<List<Message>>(emptyList())
    val messages = _messages.asStateFlow()


    fun listenForMessages(channelID: String) {
        messagesRepository.listenForMessages(channelID = channelID) { updatedMessage ->
            _messages.update { updatedMessage }
        }
    }

    fun sendMessage(channelID: String, messageText: String) {
        viewModelScope.launch {
            sendMessageUseCase.execute(channelID, messageText)
        }
    }
}