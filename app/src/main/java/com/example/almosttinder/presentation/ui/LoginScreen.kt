package com.example.almosttinder.presentation.ui

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.almosttinder.R
import com.example.data.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


@Composable
fun LoginScreen(fs: Firebase) {
    val fontRobotoRegular = FontFamily(Font(resId = R.font.roboto_condensed_regular))
    val fontRobotoSemiBold = FontFamily(Font(resId = R.font.roboto_condensed_semibold))

    var textUsername = ""
    var textPassword = ""

    Box(modifier = Modifier.fillMaxSize()) {

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
                value = textUsername,
                onValueChange = { textUsername = it },
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
                    unfocusedContainerColor = MaterialTheme.colorScheme.background),
                modifier = Modifier.padding(vertical = 20.dp)
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
                    unfocusedContainerColor = MaterialTheme.colorScheme.background),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(100.dp))

            Button(
                onClick = { fs.firestore.collection("data").document().set(User("Kaketo", "fjskdfjk"))},
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
