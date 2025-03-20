package com.example.almosttinder.presentation.chats.ui


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.almosttinder.R
import com.example.almosttinder.presentation.chats.ChatsViewModel
import com.example.almosttinder.presentation.navigation.navRoutes


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatListScreen(navController: NavController, vm: ChatsViewModel = viewModel() ) {

    val channels by vm.channels.collectAsState()

    val fontRobotoRegular = FontFamily(Font(resId = R.font.roboto_condensed_regular))
    val fontRobotoSemiBold = FontFamily(Font(resId = R.font.roboto_condensed_semibold))

    var text by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }


    Scaffold(
        containerColor = MaterialTheme.colorScheme.onPrimaryContainer,

        topBar = {
            TopAppBar(
                title = { Text("Chats") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(navRoutes.PROFILE_SCREEN) }) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = ""
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialog = true },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "")
            }
        }
    ) { paddingValue ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValue)
                .padding(horizontal = 20.dp)
        ) {
            items(channels) { channel ->
                Row(
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .clickable { navController.navigate("chat/${channel.id}") }
                ) {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(Color.Black)
                            .padding(paddingValue)
                            .padding(horizontal = 10.dp)
                    )
                    Text(
                        text = channel.name,
                        maxLines = 2,
                        minLines = 1,
                        fontFamily = fontRobotoRegular,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Start,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .fillMaxWidth()
                            .height(50.dp)
                            .clip(RoundedCornerShape(30.dp))
                            .background(MaterialTheme.colorScheme.secondary)
                            .padding(start = 15.dp, end = 15.dp)
                    )


                }
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Create a new channel") },
            containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
            text = {
                Column {
                    Text("Input name of channel", textAlign = TextAlign.Center)
                    TextField(
                        value = text,
                        onValueChange = { text = it },
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                            focusedContainerColor = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        showDialog = false
                        if (text.isNotBlank()) {
                            vm.addChannel(text)
                        }
                        text = ""
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = Color.White
                    )
                ) {
                    Text("Create")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        showDialog = false
                        text = ""
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = Color.White
                    )
                ) {
                    Text("Cancel")
                }
            },
            modifier = Modifier
                .wrapContentSize()
                .padding(16.dp)
        )
    }
}