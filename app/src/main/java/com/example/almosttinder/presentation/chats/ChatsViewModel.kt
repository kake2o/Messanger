package com.example.almosttinder.presentation.chats


import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.data.models.Channel
import com.google.firebase.Firebase
import com.google.firebase.database.database
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ChatsViewModel @Inject constructor() : ViewModel() {

    private val firebaseDatabase = Firebase.database

    private val _channels = MutableStateFlow<List<Channel>>(emptyList())
    val channels = _channels.asStateFlow()

    init {
        getChannels()
    }


    private fun getChannels() {
        Log.d("ViewModel", "Start")
        firebaseDatabase.getReference("channel").get()
            .addOnSuccessListener { snapshot ->
                val list = mutableListOf<Channel>()
                snapshot.children.forEach { data ->
                    val channel = Channel(data.key!!, data.value.toString())
                    list.add(channel)
                }
                _channels.value = list

            }
    }

    fun addChannel(name: String) {
        val key = firebaseDatabase.getReference("channel").push().key
        firebaseDatabase.getReference("channel").child(key!!).setValue(name).addOnSuccessListener {
            getChannels()
        }
    }
}