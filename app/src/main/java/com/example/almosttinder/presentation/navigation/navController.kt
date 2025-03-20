package com.example.almosttinder.presentation.navigation

import android.content.Context
import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.almosttinder.presentation.login.LoginViewModel
import com.example.almosttinder.presentation.chats.ui.ChatListScreen
import com.example.almosttinder.presentation.chat.ui.ChatScreen
import com.example.almosttinder.presentation.login.ui.LoginScreen
import com.example.almosttinder.presentation.profile.ui.ProfileScreen

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

        composable(route = "chat/{channelId}", arguments = listOf(
            navArgument("channelId") {
                type = NavType.StringType
            }
        )) {
            val channelId = it.arguments?.getString("channelId") ?: ""
            ChatScreen(navController = navController, channelId = channelId)
        }

        composable(route = navRoutes.CHATS_SCREEN) {
            ChatListScreen(navController = navController)
        }
    }
}