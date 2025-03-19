package com.tinder.domain.usecase

import com.tinder.domain.repository.AuthRepository

class LoginInUseCase(private val authRepository: AuthRepository) {
    suspend fun execute(email: String, password: String): Boolean {
        return authRepository.loginIn(email = email, password = password)
    }
}