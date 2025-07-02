package com.example.collegealerts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.firebase.FirebaseApp
import android.util.Log
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

//        // Other setup...
//
//        FirebaseMessaging.getInstance().token
//            .addOnCompleteListener { task ->
//                if (!task.isSuccessful) {
//                    Log.w("FCM", "Fetching FCM registration token failed", task.exception)
//                    return@addOnCompleteListener
//                }
//
//                val token = task.result
//                Log.d("FCM", "FCM token (forced fetch): $token")
//                // You can also send this token to your backend here
//            }
//

        setContent {
            App()
        }
    }
}
