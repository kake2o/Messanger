package com.example.almosttinder.presentation.chat.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.almosttinder.presentation.chat.ChatViewModel
import com.example.almosttinder.ui.theme.ReceiverMessage
import com.example.almosttinder.ui.theme.SenderMessage
import com.google.firebase.Firebase
import com.google.firebase.auth.auth


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(navController: NavController, channelId: String, vm: ChatViewModel = viewModel()) {

    LaunchedEffect(key1 = true) {
        vm.listenForMessages(channelId)
    }

    val messages by vm.messages.collectAsState()
    var messageText by remember { mutableStateOf("") }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.onPrimaryContainer,

        topBar = {
            TopAppBar(
                title = { Text("Chat") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.Person, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.onPrimaryContainer) // Фон строки ввода
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = messageText,
                    onValueChange = { messageText = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    placeholder = { Text("Type a message...") },
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = MaterialTheme.colorScheme.primary,
                        focusedContainerColor = MaterialTheme.colorScheme.primary
                    ),
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                if (messageText.isNotBlank()) {
                                    vm.sendMessage(channelId, messageText)
                                    messageText = ""
                                }
                            }
                        ) {
                            Icon(imageVector = Icons.Default.Send, contentDescription = "Send message")
                        }
                    }
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(8.dp)
        ) {
            items(messages) { message ->
                val isCurrentUser = message.senderId == Firebase.auth.currentUser?.uid
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = if (isCurrentUser) Arrangement.End else Arrangement.Start
                ) {
                    Box(
                        modifier = Modifier
                            .padding(8.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(if (isCurrentUser) SenderMessage else ReceiverMessage)
                            .padding(12.dp)
                    ) {
                        Text(
                            text = message.message,
                            color = Color.White,
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
    }
}