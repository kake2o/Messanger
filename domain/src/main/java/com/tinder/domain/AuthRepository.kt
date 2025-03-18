package com.tinder.domain

interface AuthRepository {
    suspend fun signUp(email: String, password: String): Boolean
}