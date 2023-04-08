package com.crystal.stopwatch.ui.component

import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import com.crystal.stopwatch.R

@Composable
fun CountDownButton(
    modifier: Modifier = Modifier,
    isPlaying: Boolean,
    optionSelected: () -> Unit
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(top = 90.dp))
    {

        Button(
            onClick = {
                optionSelected()
            },
            modifier =
            Modifier
                .height(70.dp)
                .width(200.dp),

            shape = RoundedCornerShape(25.dp),

            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.pink),
                contentColor = colorResource(id = R.color.white),
            ),

            )

        {
            val pair = if (!isPlaying) {
                "START"
            } else {
                "STOP"
            }

            Text(
                pair,
                fontSize = 20.sp,
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.poppins_semibold)))
        }
    }


}