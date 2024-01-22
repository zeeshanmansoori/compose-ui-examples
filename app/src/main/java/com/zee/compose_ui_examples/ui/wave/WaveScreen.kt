package com.zee.compose_ui_examples.ui.wave

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun WaveScreen() {
    val waveLayers = listOf(

        WaveLayer(
            phase = 90f,
            waveFrequency = 1.6f,
            waveAmplitude = 40f,
            duration = 10_000,
            topOffsetPercent = 0.6f,
            colors = listOf(
                Color(0xFFFFEB3B),
                Color(0xFFDAA811),
            )
        ),
        WaveLayer(
            phase = 180f,
            waveFrequency = 1.6f,
            waveAmplitude = 40f,
            duration = 7_000,
            topOffsetPercent = 0.65f,
            colors = listOf(
                Color(0xFF00BCD4),
                Color(0xFF1288BD),

            )
        ),
        WaveLayer(
            phase = 0f,
            waveFrequency = 1.6f,
            waveAmplitude = 40f,
            duration = 5_000,
            topOffsetPercent = 0.7f,
            colors = listOf(
                Color(0xFFE91E63),
                Color(0xFF9C0E3C),

            )
        ),

    )

    Box(modifier = Modifier.fillMaxSize()){

        Waves(modifier = Modifier.fillMaxSize(), waveLayers = waveLayers)


    }


}

@Preview
@Composable
fun WaveScreenPreview() {
    WaveScreen()
}