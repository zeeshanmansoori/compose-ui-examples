package com.zee.compose_ui_examples.ui.wave

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import kotlin.math.PI
import kotlin.math.sin

@Composable
fun Waves(modifier: Modifier = Modifier, waveLayers: List<WaveLayer>) {

    val wasPhases = waveLayers.map { waveLayer ->
        val infiniteTransition = rememberInfiniteTransition(label = "infinite")

        infiniteTransition.animateFloat(
            initialValue = waveLayer.phase,
            targetValue = waveLayer.phase + 360f,
            animationSpec = infiniteRepeatable(
                animation = tween(waveLayer.duration, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Reverse
            ), label = "float"
        )
    }

    Canvas(modifier = modifier) {
        waveLayers.forEachIndexed { index, waveLayer ->
            drawWave(waveLayer, wasPhases[index])
        }

    }
}

private fun DrawScope.drawWave(waveLayer: WaveLayer, wavePhaseState: State<Float>) {

    val path = Path()

    val width = size.width
    val height = size.height
    val heightOffset = height * waveLayer.topOffsetPercent


    path.moveTo(0f, heightOffset)
    for (i in 1..width.toInt()) {

        path.lineTo(
            i.toFloat(),
            heightOffset + getSinY(
                waveAnimatedPhaseValue = wavePhaseState.value,
                currentPosition = i,
                width = width,
                waveFrequency = waveLayer.waveFrequency
            ) * waveLayer.waveAmplitude
        )
    }



    path.lineTo(width, heightOffset)
    path.lineTo(width, height)
    path.lineTo(0f, height)
    path.close()
    drawPath(
        path,
        brush = Brush.horizontalGradient(colors = waveLayer.colors)
    )


}


private fun getSinY(
    waveAnimatedPhaseValue: Float,
    currentPosition: Int,
    waveFrequency: Float,
    width: Float
): Float {

    val tempA = PI / width
    val tempB = PI * 2 / 360f

    return sin(waveFrequency * tempA * currentPosition + waveAnimatedPhaseValue * tempB).toFloat()
}
