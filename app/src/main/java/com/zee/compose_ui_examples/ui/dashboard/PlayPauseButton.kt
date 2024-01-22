package com.zee.compose_ui_examples.ui.dashboard

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zee.compose_ui_examples.R
import com.zee.compose_ui_examples.ui.theme.trackColor

@Composable

fun PlayPauseButton(
    modifier: Modifier = Modifier,
    isPlaying: Boolean,
) {

    val icon = if (isPlaying) R.drawable.ic_play_fill else R.drawable.ic_pause_fill

    Icon(
        modifier = modifier.size(20.dp),
        painter = painterResource(id = icon),
        contentDescription = null,
        tint = trackColor,
    )
}


@Preview
@Composable
fun PlayPauseButtonPreview() {
    PlayPauseButton(isPlaying = false)
}