package com.example.almosttinder.presentation.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
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
import com.example.almosttinder.R
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


@Composable
fun LoginScreen() {
    val auth = Firebase.auth
    val fontRobotoRegular = FontFamily(Font(resId = R.font.roboto_condensed_regular))
    val fontRobotoSemiBold = FontFamily(Font(resId = R.font.roboto_condensed_semibold))

    var textEmail by remember { mutableStateOf("") }
    var textPassword by remember { mutableStateOf("") }

    Box(modifier = Modifier
        .background(MaterialTheme.colorScheme.background)
        .fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.background_splash),
            contentDescription = "",
            modifier = Modifier.align(Alignment.TopEnd)
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
                    signUp(auth, textEmail, textPassword)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)

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


private fun signUp(auth: FirebaseAuth, email: String, password: String) {
    auth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener {
            if (it.isSuccessful) {
                Log.d("SingUp", "Success")
            } else {
                Log.d("SingUp", "Failure")
            }
        }
}