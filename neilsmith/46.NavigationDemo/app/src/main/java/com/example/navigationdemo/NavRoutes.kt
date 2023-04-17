package com.example.navigationdemo

sealed class NavRoutes(private val route: String) {
    object Home : NavRoutes("home")
    object Welcome : NavRoutes("welcome")
    object Profile : NavRoutes("profile")
}
