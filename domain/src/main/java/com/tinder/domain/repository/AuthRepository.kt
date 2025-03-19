package com.tinder.domain.repository

interface AuthRepository {
    suspend fun signUp(email: String, password: String): Boolean
    suspend fun loginIn(email: String, password: String): Boolean
    suspend fun delete(email: String, password: String): Boolean
}