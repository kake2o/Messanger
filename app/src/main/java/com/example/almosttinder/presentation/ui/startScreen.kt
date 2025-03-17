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
    Box(modifier = Modifier.fillMaxSize()) {

        Column(modifier = Modifier.align(Alignment.Center)) {
            Text(
                "Sign in",
                modifier = Modifier.padding(40.dp).align(Alignment.CenterHorizontally)
            )

            Text(
                text = "Username",
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 20.dp)
            )

            Text(
                text = "Password",
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(100.dp))

            IconButton(
                onClick = {TODO()},
                modifier = Modifier.width(100.dp),
                colors = IconButtonDefaults.iconButtonColors(MaterialTheme.colorScheme.primary)
            ) {
                Text("Login", textAlign = TextAlign.Center)
            }
        }
    }
}

@Preview
@Composable
fun PreviewScreen() {
    AlmostTinderTheme {
        StartScreen()
    }
}