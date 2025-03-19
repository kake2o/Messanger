package com.example.almosttinder.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.rememberNavController
import com.example.almosttinder.presentation.login.LoginViewModel
import com.example.almosttinder.presentation.navigation.NavHostController
import com.example.almosttinder.presentation.navigation.navRoutes
import com.example.almosttinder.ui.theme.AlmostTinderTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    private val vm: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        auth = Firebase.auth
        setContent {

            val isUserLoggedIn by vm.isUserLoggedIn.collectAsState()

            val navController = rememberNavController()
            AlmostTinderTheme(dynamicColor = false) {
                NavHostController(
                    navController = navController,
                    startDestination = if (isUserLoggedIn) navRoutes.CHATS_SCREEN else navRoutes.LOGIN_SCREEN,
                    context = applicationContext,
                    viewModel = vm
                )
            }
        }
    }
}



