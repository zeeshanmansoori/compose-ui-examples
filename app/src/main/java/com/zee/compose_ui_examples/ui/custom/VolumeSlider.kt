package com.zee.compose_ui_examples.ui.custom

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.zee.compose_ui_examples.ui.theme.orangeColor
import com.zee.compose_ui_examples.ui.theme.pinkColor
import com.zee.compose_ui_examples.utils.Constants
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin


private const val SWEEP_ANGLE = 180f
private fun Int.progressToDegree(): Float {
    return SWEEP_ANGLE * this / 100
}

private fun Float.degreeToProgress(): Float {
    return this * 100 / SWEEP_ANGLE
}


private fun Float.degreeToRadian(): Float {
    return this * PI.toFloat() / SWEEP_ANGLE
}

private fun getAngleFromCoordinate(x: Float, y: Float): Float {
    val angle = atan2(
        x, y
    ) * (SWEEP_ANGLE / PI).toFloat()

    return (angle)
}

@Composable
fun VolumeSlider(
    modifier: Modifier = Modifier,
    progress: Int,
    progressTrackColors: List<Color>,
    trackColor: Color = com.zee.compose_ui_examples.ui.theme.trackColor,
    strokeWidth: Dp = 5.dp,
    onPositionChange: (Int) -> Unit = {}
) {
    var circleCenter by remember {
        mutableStateOf(Offset.Zero)
    }

    var circleProgress by remember {
        mutableStateOf(progress)
    }

    var changeAngle by remember {
        mutableStateOf(0f)
    }

    var dragStartedAngle by remember {
        mutableStateOf(0f)
    }

    var oldProgress by remember {
        mutableStateOf(progress)
    }

    val startAngle = 180f
    val startAngleRadian = startAngle.degreeToRadian()

    fun onDragStarted(offset: Offset) {
        dragStartedAngle = getAngleFromCoordinate(
            x = circleCenter.y - offset.y,
            y = circleCenter.x - offset.x,
        )
    }

    fun onDragging(offset: Offset) {

        println("zeeshan onDragging $offset")
        val touchAngle = getAngleFromCoordinate(
            circleCenter.y - offset.y,
            circleCenter.x - offset.x,
        )


        changeAngle = touchAngle - dragStartedAngle
        println("zeeshan change $changeAngle touchAngle $touchAngle")


        val lowerThreshold = oldProgress.progressToDegree() - 10.progressToDegree()
        val upperThreshold = oldProgress.progressToDegree() + 10.progressToDegree()


        if (dragStartedAngle in lowerThreshold..upperThreshold) {
            var newProgress = (oldProgress + changeAngle.degreeToProgress()).roundToInt()

            if (newProgress < 0) {
                val isInSecondQuadrant = offset.x <= circleCenter.x && offset.y >= circleCenter.y

                val isInFourthQuadrant = offset.x > circleCenter.x && offset.y >= circleCenter.y

                val caseProgress = if (isInSecondQuadrant) 0
                else if (isInFourthQuadrant) 100
                else null

                caseProgress ?: return
                newProgress = caseProgress
            }

            circleProgress = newProgress.coerceAtLeast(0).coerceAtMost(100)


        }

    }

    fun onDragEnd() {
        oldProgress = circleProgress
        onPositionChange.invoke(circleProgress)
    }


    Canvas(modifier = modifier
        .fillMaxWidth()
        .pointerInput(true) {
            detectTapGestures(onTap = {
                dragStartedAngle = circleProgress.progressToDegree()
                onDragging(it)
                onDragEnd()
            })

        }
        .pointerInput(false) {
            detectDragGestures(onDragStart = { offset ->
                onDragStarted(offset)

            }, onDrag = { change, _ ->
                onDragging(change.position)
            }, onDragEnd = {
                onDragEnd()
            })
        }) {
        val strokeWidthPx = strokeWidth.toPx()
        val innerPadding = strokeWidthPx / 2
        val radius = size.width / 2 - innerPadding
        val arcWidth = radius * 2
        val topLeft = Offset((size.width - arcWidth) / 2, (size.height - radius) / 2)
        circleCenter = Offset(size.width / 2, topLeft.y + radius)

        drawArc(
            color = trackColor,
            startAngle = startAngle,
            sweepAngle = SWEEP_ANGLE,
            useCenter = false,
            size = Size(arcWidth, arcWidth),
            style = Stroke(width = strokeWidthPx, cap = StrokeCap.Round),
            topLeft = topLeft
        )

        drawArc(
            brush = Brush.radialGradient(
                progressTrackColors.reversed(),
                center,
                radius,
            ),
            startAngle = startAngle,
            sweepAngle = circleProgress.progressToDegree(),
            useCenter = false,
            size = Size(arcWidth, arcWidth),
            style = Stroke(width = strokeWidthPx, cap = StrokeCap.Round),
            topLeft = topLeft
        )

        val calculatedAngle = circleProgress.progressToDegree().degreeToRadian() + startAngleRadian
        val offsetX = radius * cos(calculatedAngle)
        val offsetY = radius * sin(calculatedAngle)
        val thumbCenter = Offset(offsetX, offsetY) + circleCenter

        val thumbSize = Constants.THUMB_SIZE.dp.toPx() / 2f

        drawCircle(
            brush = Brush.radialGradient(
                progressTrackColors,
                thumbCenter,
                radius = thumbSize,
            ),
            radius = thumbSize,
            center = thumbCenter,

            )

//        drawCircle(
//            color = Color.White,
//            radius = thumbSize / 2.5f,
//            center = thumbCenter,
//        )


    }

}

@Composable
@Preview
private fun VolumeSliderPreview() {
    VolumeSlider(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(Color.DarkGray)
            .padding(20.dp),
        progress = 9,
        progressTrackColors = listOf(orangeColor, pinkColor)
    )
}