package com.example.collegealerts

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import androidx.navigation.compose.rememberNavController
import com.example.collegealerts.uiux.*

@Composable
fun App() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") { SplashScreen(navController) }
        composable("main") { MainScreen(navController) }
        composable("addEvent") { AddEventScreen(navController) }
        composable("details/{eventId}") { backStackEntry ->
            EventDetailScreen(backStackEntry)
        }
    }
}
