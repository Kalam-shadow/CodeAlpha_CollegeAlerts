package com.example.collegealerts.utils

import android.app.Activity
import android.content.Intent
import android.util.Log
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class GoogleAuthUiClient(private val activity: Activity) {
    private val oneTapClient: SignInClient = Identity.getSignInClient(activity)
    private val auth = Firebase.auth

    private val signInRequest: BeginSignInRequest = BeginSignInRequest.builder()
        .setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                .setServerClientId("YOUR_WEB_CLIENT_ID_HERE") // <- Replace!
                .setFilterByAuthorizedAccounts(false)
                .build()
        )
        .build()

    fun signIn() {
        oneTapClient.beginSignIn(signInRequest)
            .addOnSuccessListener { result ->
                activity.startIntentSenderForResult(
                    result.pendingIntent.intentSender,
                    1001, null, 0, 0, 0, null
                )
            }
            .addOnFailureListener {
                Log.e("GoogleAuth", "Failed: ${it.message}")
            }
    }

    fun handleSignInResult(data: Intent?, onComplete: (Boolean) -> Unit) {
        val credential = oneTapClient.getSignInCredentialFromIntent(data)
        val idToken = credential.googleIdToken
        if (idToken != null) {
            val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)
            auth.signInWithCredential(firebaseCredential)
                .addOnCompleteListener { task ->
                    onComplete(task.isSuccessful)
                }
        } else {
            onComplete(false)
        }
    }
}
