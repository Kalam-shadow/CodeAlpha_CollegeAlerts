package com.example.collegealerts.uiux

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.collegealerts.data.Event
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.google.firebase.database.FirebaseDatabase
import java.util.*

@Composable
fun AddEventScreen(navController: NavController) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }

    val categories = listOf("Seminar", "Exam", "Fest", "Notice")

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            "Create New Event",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineSmall.copy(
                fontStyle = FontStyle.Italic
            ),
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") },
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 3
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = date,
                    onValueChange = { date = it },
                    label = { Text("Date (e.g., 2025-08-01)") },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = { Icon(Icons.Default.DateRange, contentDescription = null) }
                )
                Spacer(modifier = Modifier.height(12.dp))

                Text("Select Category:", style = MaterialTheme.typography.titleSmall)
                Spacer(modifier = Modifier.height(8.dp))

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    categories.forEach { cat ->
                        AssistChip(
                            onClick = { category = cat },
                            label = { Text(cat) },
                            colors = AssistChipDefaults.assistChipColors(
                                containerColor = if (category == cat) MaterialTheme.colorScheme.secondaryContainer
                                else MaterialTheme.colorScheme.surface,
                                labelColor = if (category == cat) MaterialTheme.colorScheme.onSecondaryContainer
                                else MaterialTheme.colorScheme.onSurface,
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    onClick = {
                        val database = FirebaseDatabase.getInstance()
                        val ref = database.getReference("events")
                        val eventId = ref.push().key ?: UUID.randomUUID().toString()
                        val newEvent = Event(eventId, title, description, date, category)

                        ref.child(eventId).setValue(newEvent)
                            .addOnSuccessListener { navController.popBackStack() }
                            .addOnFailureListener {
                                // You can show snackbar or toast if failed
                            }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = title.isNotBlank() && description.isNotBlank() && date.isNotBlank() && category.isNotBlank()
                ) {
                    Text("Save Event")
                }
            }
        }
    }
}
