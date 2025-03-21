package com.example.almosttinder.di

import com.example.data.interfaces.IChannelRepository
import com.example.data.repository.ChannelRepository
import com.example.data.repository.FirebaseAuthRepository
import com.example.data.repository.MessagesRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.tinder.domain.repository.AuthRepository
import com.tinder.domain.repository.MessageRepository
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
    fun provideFirebaseDatabase(): FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }

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

    @Provides
    @Singleton
    fun provideChannelRepository(firebaseDatabase: FirebaseDatabase): IChannelRepository {
        return ChannelRepository(database = firebaseDatabase)
    }

    @Provides
    @Singleton
    fun provideMessageRepository(
        firebaseDatabase: FirebaseDatabase,
        firebaseAuth: FirebaseAuth
    ): MessageRepository {
        return MessagesRepository(firebaseDatabase, firebaseAuth)
    }
}