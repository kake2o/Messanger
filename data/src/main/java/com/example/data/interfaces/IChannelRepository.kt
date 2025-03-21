package com.example.data.interfaces

import com.example.data.models.Channel

interface IChannelRepository {
    fun getChannels(callback: (List<Channel>) -> Unit)
    fun addChannel(name: String, onComplete: () -> Unit = {})
}