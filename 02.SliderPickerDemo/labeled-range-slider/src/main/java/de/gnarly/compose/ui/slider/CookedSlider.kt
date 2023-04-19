package de.gnarly.compose.ui.slider

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp


// from LabeledRangeSlider by crystal 20230419
@Composable
fun <T : Number> StartOffsetSlider(
    selectedLowerBound: T,
    selectedUpperBound: T,
    steps: List<T>,
    onRangeChanged: (lower: T, upper: T) -> Unit,
    modifier: Modifier = Modifier,
    sliderConfig: SliderConfiguration = SliderConfiguration()
) {
    require(steps.size > 2) { "List of steps has to be at least of size 2" }
    require(steps.contains(selectedLowerBound)) { "selectedLowerBound has to be part of the provided steps" }
    require(steps.contains(selectedUpperBound)) { "selectedUpperBound has to be part of the provided steps" }

    var touchInteractionState by remember { mutableStateOf<TouchInteraction>(TouchInteraction.NoInteraction) }
    var moveLeft by remember { mutableStateOf(false) }
    var moveRight by remember { mutableStateOf(false) }

    var composableSize by remember { mutableStateOf(IntSize(0, 0)) }

    val currentDensity = LocalDensity.current
    val sizeAndDensity = composableSize to currentDensity

    val height = remember(key1 = sliderConfig) { sliderConfig.touchCircleRadius * 2 + sliderConfig.textSize.value.dp + sliderConfig.textOffset }
    val barYCenter = sizeAndDensity.derive { composableSize.height - sliderConfig.touchCircleRadiusPx }
    val barXStart = sizeAndDensity.derive { sliderConfig.touchCircleRadiusPx - sliderConfig.stepMarkerRadiusPx }
    val barYStart = sizeAndDensity.derive { barYCenter - sliderConfig.barHeightPx / 2 }
    val barWidth = sizeAndDensity.derive { composableSize.width - 2 * barXStart }
    val barCornerRadius = sizeAndDensity.derive { CornerRadius(sliderConfig.barCornerRadiusPx, sliderConfig.barCornerRadiusPx) }

    val (stepXCoordinates, stepSpacing) = sizeAndDensity.derive(steps) {
        calculateStepCoordinatesAndSpacing(
            numberOfSteps = steps.size,
            barXStart = barXStart,
            barWidth = barWidth,
            stepMarkerRadius = sliderConfig.stepMarkerRadiusPx
        )
    }

    var leftCirclePosition by remember(key1 = composableSize) {
        val lowerBoundIdx = steps.indexOf(selectedLowerBound)
        mutableStateOf(Offset(stepXCoordinates[lowerBoundIdx], barYCenter))
    }
    var rightCirclePosition by remember(key1 = composableSize) {
        val upperBoundIdx = steps.indexOf(selectedUpperBound)
        mutableStateOf(Offset(stepXCoordinates[upperBoundIdx], barYCenter))
    }

    Canvas(
        modifier = modifier
            .height(height)
            .onSizeChanged {
                composableSize = it
            }
            .touchInteraction(remember { MutableInteractionSource() }) {
                touchInteractionState = it
            }
    ) {
        drawRoundRect(
            color = sliderConfig.barColor,
            topLeft = Offset(barXStart, barYStart),
            size = Size(barWidth, sliderConfig.barHeightPx),
            cornerRadius = barCornerRadius
        )

        drawRect(
            color = sliderConfig.barColorInRange,
            topLeft = Offset(leftCirclePosition.x, barYStart),
            size = Size(rightCirclePosition.x - leftCirclePosition.x, sliderConfig.barHeightPx)
        )

        drawStepMarkersAndLabels(
            steps = steps,
            stepXCoordinates = stepXCoordinates,
            leftCirclePosition = leftCirclePosition,
            rightCirclePosition = rightCirclePosition,
            barYCenter = barYCenter,
            sliderConfig = sliderConfig
        )

        drawCircleWithShadow(
            leftCirclePosition,
            moveLeft,
            sliderConfig
        )

        drawCircleWithShadow(
            rightCirclePosition,
            moveRight,
            sliderConfig
        )
    }

    handleTouch(
        leftCirclePosition = leftCirclePosition,
        rightCirclePosition = rightCirclePosition,
        moveLeft = moveLeft,
        moveRight = moveRight,
        stepXCoordinates = stepXCoordinates,
        stepSpacing = stepSpacing,
        touchInteraction = touchInteractionState,
        updateLeft = { position, move ->
            leftCirclePosition = position
            moveLeft = move
        },
        updateRight = { position, move ->
            rightCirclePosition = position
            moveRight = move
        },
        onTouchInteractionChanged = { touchInteractionState = it },
        onRangeIdxChanged = { lowerBoundIdx, upperBoundIdx -> onRangeChanged(steps[lowerBoundIdx], steps[upperBoundIdx]) }
    )
}
