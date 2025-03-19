package com.example.data.Repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.tinder.domain.repository.AuthRepository
import kotlinx.coroutines.tasks.await

class FirebaseAuthRepository(private val firebaseAuth: FirebaseAuth) : AuthRepository {
    override suspend fun signUp(email: String, password: String): Boolean {
        return try {
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            true
        } catch (e: Exception) {
            Log.d("Repository", "$e Error")
            false
        }
    }

    override suspend fun loginIn(email: String, password: String): Boolean {
        return try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
            true
        } catch (e: Exception) {
            Log.d("Repository", "$e Error")
            false
        }
    }

    override suspend fun delete(email: String, password: String): Boolean {
        return try {
            firebaseAuth.currentUser?.delete()
            true
        } catch (e: Exception) {
            Log.d("Repository", "$e Error")
            false
        }
    }
}