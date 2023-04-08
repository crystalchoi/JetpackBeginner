package com.crystal.codapizza.ui

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.text.intl.Locale

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.crystal.codapizza.R
import com.crystal.codapizza.data.Pizza
import com.crystal.codapizza.data.Topping
import com.crystal.codapizza.data.ToppingPlacement
import java.text.NumberFormat


@Preview
@Composable
fun PizzaBuilderScreen(modifier: Modifier = Modifier) {

    var pizza by rememberSaveable{ mutableStateOf(Pizza()) }

    Column(
        modifier = modifier

    ) {


        ToppingsList(pizza = pizza, onEditPizza = {
            pizza = it
                                                  },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f, fill = true)
        )
        OrderButton(pizza, modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
            onClickButton = {
                Log.d("Pizza", "$pizza.price")
            })
    }

}



@Composable
private fun ToppingsList(pizza: Pizza, onEditPizza: (Pizza)-> Unit, modifier: Modifier = Modifier)
{

    var showToppingPlacementDialog by rememberSaveable { mutableStateOf(false) }
    var toppingForDialog by remember { mutableStateOf(Topping.Basil) }
    var toppingPlacement by remember { mutableStateOf(ToppingPlacement.All) }

    if (showToppingPlacementDialog) {
        ToppingPlacementDialog(topping = toppingForDialog
            , onDismissRequest = {  showToppingPlacementDialog = false  }
            , onToppingPlacementEdit = {
                toppingPlacement = it
                showToppingPlacementDialog = false
            }
        )
    }

    LazyColumn (modifier = modifier) {
        item {
            PizzaHeroImage(pizza = pizza, modifier = Modifier.padding(16.dp))
        }

        items(Topping.values()) { topping ->

            ToppingCell(
                topping = topping,
                placement = pizza.toppings[topping],
                onClickTopping = {
//                    val isOnPizza = pizza.toppings[topping] != null
//                    if (!isOnPizza) {
//                        showToppingPlacementDialog = true
//                    }
//                    val newPizza = pizza.withTopping(topping = topping,
//                        placement = if (isOnPizza) null else { ToppingPlacement.All })
//
//                    onEditPizza(newPizza)
                    showToppingPlacementDialog = true
                    toppingForDialog = topping
                    val newPizza = pizza.withTopping(topping = topping, placement = toppingPlacement)
                    onEditPizza(newPizza)
                }
                , modifier = modifier
            )
        }
    }
}


@Composable
private fun OrderButton(pizza: Pizza, onClickButton: () -> Unit,
    modifier: Modifier = Modifier )
{
    val context = LocalContext.current

    Button(
        modifier = modifier,
        onClick = {
            onClickButton()
            Toast.makeText(context, R.string.order_placed_toast, Toast.LENGTH_LONG)
                .show()
        }) {
        val currencyFormatter = remember { NumberFormat.getCurrencyInstance() }
        val price = currencyFormatter.format(pizza.price)
        Text(

            text = stringResource(R.string.place_order_button, price)
                .toUpperCase(Locale.current)
            // .upperCase()  ??
        )
    }
}