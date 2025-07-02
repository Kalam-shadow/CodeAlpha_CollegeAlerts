package com.example.collegealerts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.firebase.FirebaseApp
import android.util.Log
import com.example.collegealerts.ui.theme.CollegealertsTheme
import com.google.firebase.messaging.FirebaseMessaging



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(this)

        FirebaseMessaging.getInstance().subscribeToTopic("allStudents")
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("FCM", "Subscribed to topic allStudents")
                }
            }

        setContent {
            CollegealertsTheme {
                App()
            }
        }
    }
}
