package com.example.sliderpickerdemo.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.gnarly.compose.ui.slider.LabeledRangeSlider
import de.gnarly.compose.ui.slider.StartOffsetSlider


@Composable
fun StartOffsetSliderDemo() {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {

        val steps = (0..100).step(5).toList()
        var lowerBound by rememberSaveable { mutableStateOf(steps[4]) }
        val upperBound by rememberSaveable { mutableStateOf(steps[steps.size - 1]) }

        StartOffsetSlider(
            selectedLowerBound = lowerBound,
            selectedUpperBound = upperBound,
            steps = steps,
            onRangeChanged = { lower, upper ->
                lowerBound = lower
//                upperBound = upper
//                Log.i("TAG", "Updated selected range ${lowerBound..upperBound}")
                Log.i("TAG", "Updated selected range ${lowerBound}")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.size(16.dp))
        Divider()
        Spacer(modifier = Modifier.size(16.dp))

        Text(
            text = "The selected range is ${lowerBound}",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

    }



}


@Composable
fun LabeledRangeSliderDemo() {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {

        val steps = (0..50).step(10).toList()
        var lowerBound by rememberSaveable { mutableStateOf(steps[1]) }
        var upperBound by rememberSaveable { mutableStateOf(steps[steps.size - 2]) }

        LabeledRangeSlider(
            selectedLowerBound = lowerBound,
            selectedUpperBound = upperBound,
            steps = steps,
            onRangeChanged = { lower, upper ->
                lowerBound = lower
                upperBound = upper
                Log.i("TAG", "Updated selected range ${lowerBound..upperBound}")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.size(16.dp))
        Divider()
        Spacer(modifier = Modifier.size(16.dp))

        Text(
            text = "The selected range is ${lowerBound..upperBound}",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

    }



}
