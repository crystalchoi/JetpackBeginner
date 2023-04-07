package com.crystal.codapizza.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.crystal.codapizza.R
import com.crystal.codapizza.data.Pizza
import com.crystal.codapizza.data.Topping
import com.crystal.codapizza.data.ToppingPlacement



@Composable
fun PizzaHeroImage(
    pizza: Pizza,
    modifier: Modifier = Modifier ){

    Box(modifier = modifier.aspectRatio(1f)) {
        Image(
            painter = painterResource(R.drawable.pizza_crust),
            contentDescription = stringResource(id = R.string.pizza_preview),
            modifier = Modifier.fillMaxSize()
        )
        pizza.toppings.forEach { (topping, placement) ->
            Image(
                painter = painterResource(topping.pizzaOverlayImage),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.focusable(false)
                    .aspectRatio(when (placement) {
                        ToppingPlacement.Left, ToppingPlacement.Right -> 0.5f
                        ToppingPlacement.All -> 1f
                    })
                    .align(when (placement) {
                    ToppingPlacement.Left -> Alignment.CenterStart
                    ToppingPlacement.Right -> Alignment.CenterEnd
                    ToppingPlacement.All -> Alignment.Center
                }),
                alignment = when (placement) {
                    ToppingPlacement.Left -> Alignment.TopStart
                    ToppingPlacement.Right -> Alignment.TopEnd
                    ToppingPlacement.All -> Alignment.Center
                }
            )
        }
    }

}


@Preview
@Composable
private fun PizzaHeroImagePreview() {
    PizzaHeroImage(
        pizza = Pizza(
            toppings = mapOf(
                Topping.Pineapple to ToppingPlacement.All,
                Topping.Pepperoni to ToppingPlacement.Left,
                Topping.Basil to ToppingPlacement.Right
            ) )
    )
}