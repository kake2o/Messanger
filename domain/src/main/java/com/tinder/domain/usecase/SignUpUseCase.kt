package com.tinder.domain.usecase

import com.tinder.domain.repository.AuthRepository

class SignUpUseCase(private val authRepository: AuthRepository) {
    suspend fun execute(email: String, password: String): Boolean {
        return authRepository.signUp(email = email, password = password)
    }
}