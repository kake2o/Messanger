package com.example.almosttinder.presentation.chats

import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.database.database
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChatsViewModel @Inject constructor() : ViewModel() {

    private val firebaseDatabase = Firebase.database

}