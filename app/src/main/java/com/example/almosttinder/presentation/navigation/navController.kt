package com.example.almosttinder.presentation.navigation

import android.content.Context
import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.almosttinder.presentation.LoginViewModel
import com.example.almosttinder.presentation.ui.ChatListScreen
import com.example.almosttinder.presentation.ui.ChatScreen
import com.example.almosttinder.presentation.ui.LoginScreen
import com.example.almosttinder.presentation.ui.ProfileScreen

@Composable
fun NavHostController(navController: NavHostController, startDestination: String, context: Context, viewModel: LoginViewModel) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = navRoutes.LOGIN_SCREEN) {
            LoginScreen(navController = navController, context = context, viewModel = viewModel)
        }
        composable(route = navRoutes.PROFILE_SCREEN) {
            ProfileScreen(navController = navController, viewModel = viewModel, context = context)
        }
        composable(route = navRoutes.CHAT_SCREEN) {
            ChatScreen()
        }
        composable(route = navRoutes.CHATS_SCREEN) {
            ChatListScreen(navController = navController)
        }
    }
}