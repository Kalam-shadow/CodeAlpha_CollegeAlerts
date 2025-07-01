package com.example.collegealerts.utils

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class NotificationHandler : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d("NotificationHandler", "From: ${remoteMessage.from}")
        remoteMessage.notification?.let {
            Log.d("NotificationHandler", "Message Notification Body: ${it.body}")
            // Show local notification logic if needed
        }
    }

    override fun onNewToken(token: String) {
        Log.d("NotificationHandler", "Refreshed token: $token")
    }
}
