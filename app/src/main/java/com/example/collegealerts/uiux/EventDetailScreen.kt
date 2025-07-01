package com.example.collegealerts.uiux

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry

@Composable
fun EventDetailScreen(backStackEntry: NavBackStackEntry) {
    val eventId = backStackEntry.arguments?.getString("eventId") ?: "Unknown"

    // Just placeholder for now
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Event Details", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Event ID: $eventId")
        Text("Title: (Load from Firebase)")
        Text("Description: ...")
    }
}
