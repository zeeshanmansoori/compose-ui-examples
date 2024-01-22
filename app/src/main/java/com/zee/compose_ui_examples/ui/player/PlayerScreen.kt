package com.zee.compose_ui_examples.ui.player

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zee.compose_ui_examples.ui.MainActivity
import com.zee.compose_ui_examples.ui.player.component.PlayerBottomBar
import com.zee.compose_ui_examples.ui.player.component.PlayerController
import com.zee.compose_ui_examples.ui.player.component.PlayerLinearProgressSlider
import com.zee.compose_ui_examples.ui.player.component.VolumeSliderWithAlbum

@Composable
fun PlayerScreen() {


    val audioDuration = 5 * 60 //secs


    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        VolumeSliderWithAlbum(
            modifier = Modifier
                .padding(10.dp)
                .padding(top = 30.dp)
        )
        Text(
            modifier = Modifier
                .padding(20.dp),
            text = "Feel the beat",
            fontSize = 18.sp,
            style = TextStyle(fontWeight = FontWeight.Bold, color = Color.Black)
        )
        Text(
            modifier = Modifier
                .padding(vertical = 10.dp),
            text = "DJ Snake",
            fontSize = 16.sp,
            style = TextStyle(fontWeight = FontWeight.Light)
        )

        PlayerLinearProgressSlider(
            modifier = Modifier
                .padding(horizontal = 20.dp),
            colors = MainActivity.gradientColors(),
            audioDuration = audioDuration
        )

        Spacer(Modifier.weight(1f))

        PlayerController(
            modifier = Modifier
                .padding(vertical = 20.dp)
                .fillMaxWidth()
        )

        Spacer(Modifier.weight(1f))

        PlayerBottomBar()
    }
}

@Preview(showBackground = true)
@Composable
fun PlayerScreenPreview() {
    PlayerScreen()
}