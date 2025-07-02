package com.example.collegealerts.uiux

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import com.example.collegealerts.data.Event
import com.google.firebase.database.FirebaseDatabase

@Composable
fun EventDetailScreen(backStackEntry: NavBackStackEntry) {
    val eventId = backStackEntry.arguments?.getString("eventId") ?: return

    var event by remember { mutableStateOf<Event?>(null) }

    LaunchedEffect(eventId) {
        val ref = FirebaseDatabase.getInstance().getReference("events").child(eventId)
        ref.get().addOnSuccessListener { snapshot ->
            event = snapshot.getValue(Event::class.java)
        }.addOnFailureListener {
        }
    }

    if (event == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {
            Text(text = event!!.title, style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "📅 ${event!!.date}", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Category: ${event!!.category}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = event!!.description, style = MaterialTheme.typography.bodyLarge)
        }
    }
}
