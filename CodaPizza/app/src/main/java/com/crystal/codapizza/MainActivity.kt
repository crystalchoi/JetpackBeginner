package com.crystal.codapizza

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.crystal.codapizza.data.Topping
import com.crystal.codapizza.data.ToppingPlacement
import com.crystal.codapizza.ui.PizzaBuilderScreen
import com.crystal.codapizza.ui.ToppingCell


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PizzaBuilderScreen()
        }
    }
}