package com.example.data.repository

import com.example.data.interfaces.IChannelRepository
import com.example.data.models.Channel
import com.google.firebase.database.FirebaseDatabase

class ChannelRepository(
    private val database: FirebaseDatabase
) : IChannelRepository {
    override fun getChannels(callback: (List<Channel>) -> Unit) {
        database.getReference("channel").get()
            .addOnSuccessListener { snapshot ->
                val list = snapshot.children.map { data ->
                    Channel(data.key!!, data.value.toString())
                }
                callback(list)
            }
    }

    override fun addChannel(name: String, onComplete: () -> Unit) {
        val key = database.getReference("channel").push().key!!
        database.getReference("channel").child(key).setValue(name)
            .addOnSuccessListener { onComplete() }
    }
}