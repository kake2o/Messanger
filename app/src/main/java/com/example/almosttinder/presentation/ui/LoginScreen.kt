package com.example.almosttinder.presentation.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.almosttinder.R
import com.example.almosttinder.presentation.LoginViewModel
import com.example.almosttinder.presentation.navigation.navRoutes



@Composable
fun LoginScreen(viewModel: LoginViewModel, navController: NavController, context: Context) {
    val fontRobotoRegular = FontFamily(Font(resId = R.font.roboto_condensed_regular))
    val fontRobotoSemiBold = FontFamily(Font(resId = R.font.roboto_condensed_semibold))

    var textEmail by remember { mutableStateOf("") }
    var textPassword by remember { mutableStateOf("") }

    val signUpResult = viewModel.signUpResult.collectAsState()
    val signInResult = viewModel.signInResult.collectAsState()
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.splash),
            contentDescription = "",
            modifier = Modifier
                .align(alignment = Alignment.TopEnd)
                .size(200.dp)
        )
        Column(modifier = Modifier.align(Alignment.Center)) {
            Text(
                stringResource(id = R.string.greetingLogin),
                fontFamily = fontRobotoSemiBold,
                fontSize = 24.sp,
                modifier = Modifier
                    .padding(80.dp)
                    .align(Alignment.CenterHorizontally)
            )
            TextField(
                value = textEmail,
                onValueChange = { textEmail = it },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.loginUsername),
                        textAlign = TextAlign.Center,
                        fontFamily = fontRobotoRegular,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    )
                },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = MaterialTheme.colorScheme.background,
                    focusedContainerColor = MaterialTheme.colorScheme.background
                ),
                modifier = Modifier
                    .padding(vertical = 20.dp)
                    .align(Alignment.CenterHorizontally)
            )
            TextField(
                value = textPassword,
                onValueChange = { textPassword = it },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.loginPassword),
                        textAlign = TextAlign.Center,
                        fontFamily = fontRobotoRegular,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = MaterialTheme.colorScheme.background,
                    focusedContainerColor = MaterialTheme.colorScheme.background
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(100.dp))

            Button(
                onClick = {
                    viewModel.signUp(textEmail, textPassword)
                    signUpResult?.let { result ->
                        if (result.value) {
                            navController.navigate(navRoutes.CHATS_SCREEN)
                        }
                        else {
                            Toast.makeText(context, "Failed to Sign Up", Toast.LENGTH_SHORT).show()
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)

            ) {
                Text(
                    stringResource(id = R.string.signUp),
                    textAlign = TextAlign.Center,
                    fontFamily = fontRobotoSemiBold,
                    fontSize = 18.sp,
                    color = colorResource(R.color.black)
                )
            }
            Button(
                onClick = {
                    viewModel.signIn(textEmail, textPassword)
                    signInResult?.let { result ->
                        if (result.value) {
                            navController.navigate(navRoutes.CHATS_SCREEN)
                        }
                        else {
                            Toast.makeText(context, "Failed to login", Toast.LENGTH_SHORT).show()
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)

            ) {
                Text(
                    stringResource(id = R.string.loginBtn),
                    textAlign = TextAlign.Center,
                    fontFamily = fontRobotoSemiBold,
                    fontSize = 18.sp,
                    color = colorResource(R.color.black)
                )
            }

        }
    }
}

