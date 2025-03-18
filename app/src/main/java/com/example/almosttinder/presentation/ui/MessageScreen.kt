package com.example.almosttinder.presentation.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun MessageScreen() {
    val List = 
    LazyColumn(modifier = Modifier.fillMaxSize()
        .padding(20.dp)
        .clip(RoundedCornerShape(20.dp)
        )
    ) {
        items(List)
    }
}

