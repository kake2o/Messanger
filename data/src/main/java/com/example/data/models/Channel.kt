package com.example.data.models

data class Channel(
    val id: String = "",
    val name: String = "",
    val createdAt: Long = System.currentTimeMillis()
)
