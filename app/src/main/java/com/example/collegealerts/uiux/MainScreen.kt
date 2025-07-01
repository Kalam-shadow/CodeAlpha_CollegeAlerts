package com.example.collegealerts.uiux

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.collegealerts.data.Event

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController) {
    // Fake static list to start, will replace with Firebase later
    val eventList = listOf(
        Event(id = "1", title = "Seminar on AI", description = "Explore future tech.", date = "2025-07-10", category = "Seminar"),
        Event(id = "2", title = "Annual Fest", description = "Fun and music.", date = "2025-07-20", category = "Fest")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("College Alerts") },
                actions = {
                    IconButton(onClick = { /* Notifications logic */ }) {
                        Icon(Icons.Default.Notifications, contentDescription = "Notifications")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate("addEvent")
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add Event")
            }
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding).padding(16.dp)) {
            items(eventList.size) { index ->
                val event = eventList[index]
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable {
                            navController.navigate("details/${event.id}")
                        }
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = event.title, style = MaterialTheme.typography.titleMedium)
                        Text(text = "📅 ${event.date}", style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}
