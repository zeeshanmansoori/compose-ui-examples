package com.zee.compose_ui_examples.ui.wave

import androidx.compose.ui.graphics.Color

data class WaveLayer(
    val phase: Float,
    val waveFrequency: Float,
    val waveAmplitude: Float,
    val duration: Int,
    val topOffsetPercent:Float,
    val colors:List<Color>,
)
