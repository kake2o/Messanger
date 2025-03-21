package com.example.data.repository

import com.example.data.interfaces.IMessageRepository
import com.example.data.models.Message
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.UUID
import javax.inject.Inject

class MessagesRepository @Inject constructor(private val database: FirebaseDatabase, private val auth: FirebaseAuth) : IMessageRepository {

    override fun listenForMessages(channelID: String, onMessagesUpdated: (List<Message>) -> Unit) {
        database.getReference("messages")
            .child(channelID)
            .orderByChild("createdAt")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val messages = snapshot.children.mapNotNull { it.getValue(Message::class.java) }
                    onMessagesUpdated(messages)
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
    }

    override fun sendMessage(channelID: String, messageText: String) {
        val message = Message(
            id = database.reference.key ?: UUID.randomUUID().toString(),
            senderId = Firebase.auth.currentUser?.uid ?: "",
            message = messageText,
            senderName = Firebase.auth.currentUser?.displayName ?: "",
            senderImage = null,
            imageUrl = null
        )
        val key =
            database.getReference("messages").child(channelID).push().setValue(message)

    }



}