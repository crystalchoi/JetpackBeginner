package com.example.sliderpickerdemo.ui


import android.app.Activity
import android.content.res.Resources
import android.util.Log
import android.util.TypedValue
import android.view.MotionEvent
import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.sliderpickerdemo.ui.theme.SliderPickerDemoTheme
import kotlin.math.roundToInt
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntSize

private const val TAG = "CustomSlider"

private val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()
private val Float.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()



//val pxValue = with(LocalDensity.current) { 16.dp.toPx() }
// or
//val pxValue = LocalDensity.current.run { 16.dp.toPx() }

@Composable
fun DrawSimpleCircle(
    color: Color = colorScheme.primary,
    modifier: Modifier = Modifier,
    minSize: DpSize = DpSize(32.dp, 32.dp),
    offsetX: Float,
    offsetY: Float,
) {

    val distanceFromCenter = with(LocalDensity.current) { 16.dp.toPx() }
    val centerX = offsetX.minus(distanceFromCenter)
    val centerY = offsetY.minus(distanceFromCenter)
    Canvas(modifier = Modifier
        .size(minSize)
        .offset { IntOffset(centerX.roundToInt(), centerY.roundToInt()) }
    ) {
        drawCircle(color)
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class)
@Composable
fun DrawSimpleCircleTest(
    modifier: Modifier = Modifier,
    color: Color = colorScheme.primary,
    minSize: DpSize = DpSize(24.dp, 24.dp)
) {

    var offsetX by remember { mutableStateOf(0.0f) }
    var offsetY by remember { mutableStateOf(0.0f) }
    var points by remember { mutableStateOf<List<Offset>>(emptyList()) }

    Box(modifier = Modifier.fillMaxSize()) {

//        Canvas(modifier = Modifier
//            .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
//            .combinedClickable(
//                onClick = { Log.i(TAG, "circle Canvas clicked") },
//                onLongClick = { Log.i(TAG, "circle Canvas long clicked") },
//                onDoubleClick = { Log.i(TAG, "circle Canvas double clicked") })
//            .pointerInput(key1 = Unit) {
//                detectDragGestures(onDragStart = { touch ->
//                    Log.i(TAG, "circle Dragged $touch")
//                    points = listOf(touch)
//
//                }) { change, dragAmount ->
//                    Log.i(TAG, "Circle Dragged $dragAmount; Result $change")
//                    change.consume()
//                    offsetX += dragAmount.x
//                    offsetY += dragAmount.y
//
//                    val pointsFromHistory = change.historical
//                        .map { it.position }
//                        .toTypedArray()
//                    val newPoints = listOf(*pointsFromHistory, change.position)
//                    points = points + newPoints
//                }
//            }
//        ) {
//            drawCircle(color = color, radius = 30f)
//        }

        Canvas(modifier = Modifier
            .fillMaxSize()
//            .clickable {
//                Log.i("Tag", "Canvas clicked") }
            .combinedClickable(
                onClick = { Log.i("Tag", "Canvas clicked") },
                onLongClick = { Log.i("Tag", "Canvas long clicked") },
                onDoubleClick = { Log.i("Tag", "Canvas double clicked") }
            )
            .pointerInput(key1 = Unit) {
                detectDragGestures(
                    onDragStart = { touch ->
                        Log.i(TAG, "line Dragged $touch")
                        offsetX = touch.x
                        offsetY = touch.y
                        points = listOf(touch)
                    },
                    onDrag = { change, dragAmount ->
                        Log.i(TAG, "line Dragged $dragAmount; Result $change")


//                        change.consume()
//                        offsetX += dragAmount.x
//                        offsetY += dragAmount.y

                        val pointsFromHistory = change.historical
                            .map { it.position }
                            .toTypedArray()
                        val newPoints = listOf(*pointsFromHistory, change.position)
                        points = points + newPoints
                    },
                    onDragEnd = {}
                )
            }
        ) {


            if (points.size > 1) {
                val path = Path().apply {
                    val firstPoint = points.first()
                    val rest = points.subList(1, points.size - 1)
                    offsetX = firstPoint.x
                    offsetY = firstPoint.y
                    moveTo(firstPoint.x, firstPoint.y)
                    rest.forEach {
                        lineTo(it.x, it.y)
                        offsetX = it.x
                        offsetY = it.y
                    }
                }
                drawPath(path, color, style = Stroke(width = 4.dp.toPx()))
                drawIntoCanvas {

                }
            }

        }

        if (points.isNotEmpty()) {
            DrawSimpleCircle(offsetX = offsetX, offsetY = offsetY)
        }



    }
}


@Composable
fun SimpleBar(modifier: Modifier = Modifier) {


    var composableSize by remember { mutableStateOf(IntSize(0, 0)) }
    val barWidth = remember(key1 = composableSize) { composableSize.width.toFloat() }

    Canvas(
        modifier = modifier.height(12.dp)
            .onSizeChanged {
                composableSize = it
            }
    ) {
        drawRoundRect(
            color = Color.Gray,
            topLeft = Offset(0f, 0f),
            size = Size(barWidth, 200f),
            cornerRadius = CornerRadius(40f, 40f)
        )
    }

}

@Preview(showBackground = true)
@Composable
fun DrawSimpleCirclePreview() {
    SliderPickerDemoTheme {
//        DrawSimpleCircle(offsetX = 0f, offsetY = 0f)
        SimpleBar()
    }
}