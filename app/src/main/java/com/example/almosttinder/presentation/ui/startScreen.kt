package com.example.almosttinder.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.almosttinder.ui.theme.AlmostTinderTheme

@Composable
fun StartScreen() {
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
                value = textUsername,
                onValueChange = { textUsername = it },
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
                onClick = { TODO() },
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

@Preview(showBackground = true)
@Composable
fun PreviewScreen() {
    AlmostTinderTheme(dynamicColor = false) {
        StartScreen()
    }
}