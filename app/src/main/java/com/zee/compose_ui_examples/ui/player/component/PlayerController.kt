package com.zee.compose_ui_examples.ui.player.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zee.compose_ui_examples.R
import com.zee.compose_ui_examples.ui.MainActivity
import com.zee.compose_ui_examples.ui.theme.iconColor


@Composable
fun PlayerController(modifier: Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(modifier = Modifier)
        FloatingActionButton(
            onClick = {

            },
            shape = CircleShape,
            containerColor = Color.White,
            elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(defaultElevation = 0.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.rewind_back),
                contentDescription = null,
                tint = iconColor
            )
        }


        PlayPauseButton(
            isPlaying = false,
            colors = MainActivity.gradientColors()
        ) {

        }


        FloatingActionButton(
            onClick = {

            },
            shape = CircleShape,
            containerColor = Color.White,
            elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(defaultElevation = 0.dp)
        ) {


            Icon(
                painter = painterResource(id = R.drawable.rewind_forward),
                contentDescription = null,
                tint = iconColor
            )
        }
        Box(modifier = Modifier)


    }
}

@Preview
@Composable
fun HomeControllerPreview() {
    PlayerController(modifier = Modifier.fillMaxSize())
}