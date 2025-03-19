package com.example.almosttinder.di


import com.tinder.domain.repository.AuthRepository
import com.tinder.domain.usecase.DeleteUseCase
import com.tinder.domain.usecase.LoginInUseCase
import com.tinder.domain.usecase.SignUpUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideSignUpUseCase(authRepository: AuthRepository): SignUpUseCase {
        return SignUpUseCase(authRepository = authRepository)
    }

    @Provides
    fun provideLoginInUseCase(authRepository: AuthRepository) : LoginInUseCase {
        return LoginInUseCase(authRepository = authRepository)
    }

    @Provides
    fun provideDeleteUseCase(authRepository: AuthRepository) : DeleteUseCase {
        return DeleteUseCase(authRepository = authRepository)
    }
}