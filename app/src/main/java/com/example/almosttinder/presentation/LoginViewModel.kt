package com.example.almosttinder.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.tinder.domain.usecase.DeleteUseCase
import com.tinder.domain.usecase.LoginInUseCase
import com.tinder.domain.usecase.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val auth: FirebaseAuth = Firebase.auth,
    private val signUpUseCase: SignUpUseCase,
    private val deleteUseCase: DeleteUseCase,
    private val loginInUseCase: LoginInUseCase,
) : ViewModel() {
    private val _isUserLoggedIn = MutableStateFlow(false)
    val isUserLoggedIn: StateFlow<Boolean> = _isUserLoggedIn.asStateFlow()

    init {
        checkUserLoginStatus()
    }

    private fun checkUserLoginStatus() {
        val currentUser = auth.currentUser
        _isUserLoggedIn.value = currentUser != null
    }

    private val _signUpResult = MutableStateFlow(false)
    val signUpResult: StateFlow<Boolean> = _signUpResult.asStateFlow()

    private val email = MutableStateFlow("")
    private val password = MutableStateFlow("")

    fun signUp(email: String, password: String) {
        viewModelScope.launch {
            this@LoginViewModel.email.update { email }
            this@LoginViewModel.password.update { password }
            _signUpResult.update { signUpUseCase.execute(email = email, password = password) }
        }
    }

    private val _signInResult = MutableStateFlow(false)
    val signInResult: StateFlow<Boolean> = _signInResult.asStateFlow()

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            _signInResult.update { loginInUseCase.execute(email = email, password = password)}
        }
    }

    fun signOut() {
        auth.signOut()
    }

    private val _deleteResult = MutableStateFlow(false)
    val deleteResult: StateFlow<Boolean> = _deleteResult.asStateFlow()

    fun deleteAcc() {
        viewModelScope.launch {
            _deleteResult.update { deleteUseCase.execute(email = email.value, password = password.value) }
        }
    }
}