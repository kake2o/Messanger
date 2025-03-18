package com.tinder.domain

class SignUpUseCase(private val authRepository: AuthRepository) {
    suspend fun execute(email: String, password: String): Boolean {
        return authRepository.signUp(email = email, password = password)
    }
}