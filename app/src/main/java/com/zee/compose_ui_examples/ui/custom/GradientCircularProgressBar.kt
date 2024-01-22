package com.zee.compose_ui_examples.ui.custom

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.zee.compose_ui_examples.ui.theme.orangeColor
import com.zee.compose_ui_examples.ui.theme.pinkColor


private fun Int.progressToDegree(): Float {
    return 360f * this / 100
}


@Composable
fun GradientCircularProgressBar(
    modifier: Modifier = Modifier,
    progress: Int,
    progressTrackColors: List<Color> = listOf(orangeColor, pinkColor),
    trackColor: Color = com.zee.compose_ui_examples.ui.theme.trackColor,
    strokeWidth: Dp = 5.dp,
    onPositionChange: (Int) -> Unit = {},
    defaultMinSize: Dp = 150.dp
) {


    val circleProgress by remember {
        mutableStateOf(progress)
    }


    val startAngle = -90f


    Canvas(
        modifier = modifier.defaultMinSize(defaultMinSize)
    ) {

        val strokeWidthPx = strokeWidth.toPx()
        val innerPadding = strokeWidthPx / 2
        val radius = size.width / 2 - innerPadding
        val circleCenter = Offset(size.width / 2, size.height / 2)
        val arcWidth = radius * 2


        drawCircle(
            color = trackColor,
            radius = radius,
            center = circleCenter,
            style = Stroke(strokeWidthPx)
        )


        drawArc(
            brush = Brush.radialGradient(
                progressTrackColors,
                circleCenter,
                radius,
            ),
            startAngle = startAngle,
            sweepAngle = circleProgress.progressToDegree(),
            useCenter = false,
            size = Size(arcWidth, arcWidth),
            style = Stroke(width = strokeWidthPx, cap = StrokeCap.Round),
            topLeft = Offset((size.width - arcWidth) / 2f, (size.height - arcWidth) / 2f)
        )


    }
}


@Composable
@Preview
private fun GradientCircularProgressBarPreview() {
    GradientCircularProgressBar(
        modifier = Modifier.padding(50.dp).fillMaxSize(),
        progress = 10,
    )
}