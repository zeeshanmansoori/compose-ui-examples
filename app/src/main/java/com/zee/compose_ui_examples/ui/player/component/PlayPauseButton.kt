package com.zee.compose_ui_examples.ui.player.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.zee.compose_ui_examples.R
import com.zee.compose_ui_examples.utils.onTapWithResizeAnimation

@Composable
fun PlayPauseButton(
    isPlaying: Boolean,
    colors: List<Color>,
    onPlayingChange: (Boolean) -> Unit,
) {

    var playingState by remember {
        mutableStateOf(isPlaying)
    }


    Box(
        modifier = Modifier
            .onTapWithResizeAnimation {
                playingState = !playingState
            }
            .shadow(elevation = 1.dp, shape = RoundedCornerShape(15.dp))
            .clip(RoundedCornerShape(15.dp))
            .border(width = 5.dp, color = Color.White.copy(.65f), RoundedCornerShape(15.dp))
            .background(
                brush = Brush.verticalGradient(
                    colors = colors
                )
            )
            .padding(25.dp),
    ) {
        val icon = if (playingState) R.drawable.ic_play else R.drawable.ic_pause
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = Color.White
        )
    }

}