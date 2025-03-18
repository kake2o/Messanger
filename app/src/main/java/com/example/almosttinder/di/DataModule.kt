package com.example.almosttinder.di

import com.example.data.Repository.FirebaseAuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.tinder.domain.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideAuthRepository(firebaseAuth: FirebaseAuth) : AuthRepository {
        return FirebaseAuthRepository(firebaseAuth = firebaseAuth)
    }
}