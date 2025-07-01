package com.example.collegealerts.uiux

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun AddEventScreen(navController: NavController) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        TextField(value = title, onValueChange = { title = it }, label = { Text("Title") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = description, onValueChange = { description = it }, label = { Text("Description") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = date, onValueChange = { date = it }, label = { Text("Date") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = category, onValueChange = { category = it }, label = { Text("Category") })
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            // Save to Firebase logic here
            navController.popBackStack()
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Save Event")
        }
    }
}
