package com.example.almosttinder.presentation.chats


import androidx.lifecycle.ViewModel
import com.example.data.interfaces.IChannelRepository
import com.example.data.models.Channel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ChatsViewModel @Inject constructor(
    private val channelRepository: IChannelRepository
) : ViewModel() {

    private val _channels = MutableStateFlow<List<Channel>>(emptyList())
    val channels = _channels.asStateFlow()

    init {
        getChannels()
    }

    private fun getChannels() {
        channelRepository.getChannels { list ->
            _channels.update { list }
        }
    }

    fun addChannel(name: String) {
        channelRepository.addChannel(name)
        getChannels()
    }
}