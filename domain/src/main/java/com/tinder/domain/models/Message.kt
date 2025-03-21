package com.tinder.domain.models

data class Message(
    val id: String = "",
    val message: String = "",
    val senderId: String = "",
    val createdAt: Long = System.currentTimeMillis(),
    val senderName: String = "",
    val senderImage: String? = null,
    val imageUrl: String? = null
)

