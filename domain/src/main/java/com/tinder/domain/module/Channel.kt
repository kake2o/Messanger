package com.tinder.domain.module

data class Channel(
    val id: String = "",
    val name: String = "",
    val createdAt: Long = System.currentTimeMillis()
)
