package com.crystal.codapizza.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.crystal.codapizza.R
import com.crystal.codapizza.data.Topping
import com.crystal.codapizza.data.ToppingPlacement

@Composable
fun ToppingPlacementDialog(topping: Topping,
                           onDismissRequest: ()-> Unit,
                           onToppingPlacementEdit: (ToppingPlacement)-> Unit
) {

    Dialog(onDismissRequest = onDismissRequest) {
        Card {
            Column {
                val toppingName = stringResource(topping.toppingName)
                Text(
                    text = stringResource(R.string.placement_prompt, toppingName),
                    style = MaterialTheme.typography.subtitle1,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(24.dp)
                )
                Column(modifier = Modifier.padding(bottom = 12.dp)) {

                    ToppingPlacement.values().forEach { placement ->
                        TextButton( onClick =  { onToppingPlacementEdit(placement) }) {
                            Text(
                                text = stringResource(id = placement.label),
                                style = MaterialTheme.typography.subtitle1,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(4.dp)
                                    .weight(1f)

                            )
                        }
                    }
                }
            }
        }
    }
}