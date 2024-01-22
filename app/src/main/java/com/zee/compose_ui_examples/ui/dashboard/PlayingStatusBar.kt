package com.zee.compose_ui_examples.ui.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zee.compose_ui_examples.R
import com.zee.compose_ui_examples.ui.custom.GradientCircularProgressBar
import com.zee.compose_ui_examples.utils.onTapWithResizeAnimation

@Composable
fun PlayingStatusBar(modifier: Modifier = Modifier) {

    val cardShape = RoundedCornerShape(20.dp)
    var playingState by remember {
        mutableStateOf(false)
    }
    Box(
        modifier = modifier
            .clip(cardShape)
            .background(Color.White)
            .padding(15.dp)
    ) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            val progressSize = 50.dp

            Image(
                modifier = Modifier
                    .clip(cardShape)
                    .size(progressSize + 10.dp),
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
            Column(
                Modifier
                    .weight(1f)
                    .padding(horizontal = 10.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Song Name", fontSize = 16.sp,
                    style = TextStyle(fontWeight = FontWeight.Bold, color = Color.Black)
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text = "Artist Name", fontSize = 14.sp,
                    style = TextStyle(fontWeight = FontWeight.Light, color = Color.Black)
                )
            }

            Box(modifier = Modifier.onTapWithResizeAnimation {
                playingState = !playingState
            }, contentAlignment = Alignment.Center) {
                GradientCircularProgressBar(modifier = Modifier.size(progressSize), progress = 10)
                PlayPauseButton(isPlaying = playingState)
            }


        }

    }
}

@Preview
@Composable
fun PlayingStatusBarPreview() {
    PlayingStatusBar(
        Modifier
            .padding(50.dp)
            .fillMaxWidth()
    )
}