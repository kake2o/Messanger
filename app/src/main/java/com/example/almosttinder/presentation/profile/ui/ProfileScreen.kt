package com.example.almosttinder.presentation.profile.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.almosttinder.R
import com.example.almosttinder.presentation.login.LoginViewModel
import com.example.almosttinder.presentation.navigation.navRoutes


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController, viewModel: LoginViewModel, context: Context) {

    val isDeleted = viewModel.deleteResult.collectAsState()

    Scaffold(topBar = {
        TopAppBar(title = {
            Text(
                text = "Profile", textAlign = TextAlign.Center
            )
        }, navigationIcon = {
            IconButton(
                onClick = { navController.navigate(navRoutes.CHATS_SCREEN) }
            ) {
                Icon(
                    imageVector = Icons.Default.Home, contentDescription = ""
                )
            }
        })
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color.Black)
                    .padding(paddingValues)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(100.dp))
            HorizontalDivider(modifier = Modifier.padding(horizontal = 20.dp))

            IconButton(
                onClick = {
                    viewModel.signOut()
                    navController.navigate(navRoutes.LOGIN_SCREEN)
                },
                modifier = Modifier
                    .padding(vertical = 15.dp, horizontal = 20.dp)
                    .height(60.dp)
                    .fillMaxWidth()
            ) {
                Row {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                        contentDescription = "",
                        modifier = Modifier.padding(horizontal = 10.dp)
                    )

                    Text(
                        text = stringResource(id = R.string.logOut),
                        fontSize = 16.sp,
                        color = Color.Black,
                    )
                }
            }
            IconButton(
                onClick = {
                    viewModel.deleteAcc()
                    navController.navigate(navRoutes.LOGIN_SCREEN)
                    isDeleted?.let { result ->
                        if (result.value) {
                            Toast.makeText(context, "Account deleted", Toast.LENGTH_SHORT).show()
                        }
                    }
                },
                modifier = Modifier
                    .padding(vertical = 15.dp, horizontal = 20.dp)
                    .height(60.dp)
                    .width(250.dp)
                    .background(color = Color.Red)
                    .align(Alignment.CenterHorizontally)
            ) {
                Row {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "",
                        modifier = Modifier.padding(horizontal = 10.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.delete),
                        fontSize = 16.sp,
                        color = Color.Black,
                    )
                }
            }
        }
    }
}