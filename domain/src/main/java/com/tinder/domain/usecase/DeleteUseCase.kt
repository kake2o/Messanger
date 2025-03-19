package com.tinder.domain.usecase

import com.tinder.domain.repository.AuthRepository

class DeleteUseCase(private val authRepository: AuthRepository) {
    suspend fun execute(email: String, password: String): Boolean {
        return authRepository.delete(email = email, password = password)
    }
}