package com.example.sliderpickerdemo

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.PackageManagerCompat
import androidx.core.content.PackageManagerCompat.LOG_TAG
import com.example.sliderpickerdemo.ui.DrawSimpleCircleTest
import com.example.sliderpickerdemo.ui.LabeledRangeSliderDemo
import com.example.sliderpickerdemo.ui.StartOffsetSliderDemo
import com.example.sliderpickerdemo.ui.theme.SliderPickerDemoTheme
import de.gnarly.compose.ui.slider.LabeledRangeSlider
import de.gnarly.compose.ui.slider.StartOffsetSlider

const val WEATHER_CARD_TAG = "WeatherCardTag"
const val SLIDER_TAG = "SliderTag"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SliderPickerDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Log.i(LOG_TAG, "show me LOG_TAG")
//                    SettingsScreen()
                    StartOffsetSliderDemo()
//                    LabeledRangeSliderDemo()
                }
            }
        }
    }

    private companion object {
        val LOG_TAG = MainActivity::class.simpleName
    }
}


@Composable
private fun SettingsScreen() {
    Column {

        DrawSimpleCircleTest()

        Card(
            modifier = Modifier
                .testTag(WEATHER_CARD_TAG)
                .fillMaxWidth()
                .padding(12.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            shape = RoundedCornerShape(12.dp),
        ) {
            Column(modifier = Modifier.padding(12.dp)) {

                Text(text = "Setting")
                Box(contentAlignment = Alignment.Center) {

                    GreetingSlider()
                }
            }
        }

        Column(modifier = Modifier.padding(24.dp)) {

            Text(text = "Setting")
            Box(contentAlignment = Alignment.Center) {

                GreetingSlider()
            }
        }
    }


}




@Composable
fun GreetingSlider(initialValue: Float = 0f
             , dates: List<String> = listOf("1", "2", "3"),
    modifier: Modifier = Modifier,
             onValueChange: (Int) -> Unit = {}) {

    val (sliderValue, setSliderValue) = remember { mutableStateOf(initialValue) }
    Slider(
        modifier = Modifier
            .testTag(SLIDER_TAG)
            .fillMaxWidth(),
        value = sliderValue,
        valueRange = 0f..dates.size.minus(1).toFloat(),
        steps = dates.size.minus(2),
        colors = customSliderColors(),
        onValueChange = {
            setSliderValue(it)
            onValueChange(it.toInt())
        })
}

@Composable
private fun customSliderColors(): SliderColors = SliderDefaults.colors(
    activeTickColor = Color.Transparent,
    inactiveTickColor = Color.Transparent
)


@Preview(showBackground = true, widthDp = 400, heightDp = 600)
@Composable
fun SettingsScreenPreview() {
    SliderPickerDemoTheme {
        LabeledRangeSliderDemo()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SliderPickerDemoTheme {
        GreetingSlider()
    }
}