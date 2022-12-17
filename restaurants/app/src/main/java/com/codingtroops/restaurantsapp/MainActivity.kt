package com.codingtroops.restaurantsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.codingtroops.restaurantsapp.model.Restaurant
import com.codingtroops.restaurantsapp.ui.theme.RestaurantsAppTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RestaurantsAppTheme {
                RestaurantsApp()
            }
        }
    }
}

@Composable
private fun RestaurantsApp() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "restaurants") {
        composable(route = "restaurants") {
            RestaurantsScreen()
        }
        composable(route = "restaurants/{restaurant_id}") {
            RestaurantDetailsScreen()
        }

    }

}