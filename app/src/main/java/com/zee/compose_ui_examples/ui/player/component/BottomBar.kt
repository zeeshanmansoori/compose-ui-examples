package com.zee.compose_ui_examples.ui.player.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zee.compose_ui_examples.R
import com.zee.compose_ui_examples.ui.custom.MIcon

@Composable
fun PlayerBottomBar() {
    Column(Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(0.5.dp)
            .background(color = Color.Gray))
        Row(modifier = Modifier
            .padding(vertical = 25.dp)
            .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            MIcon(iconResId = R.drawable.heart_icon)
            MIcon(iconResId = R.drawable.ic_shuffle)
            MIcon(iconResId = R.drawable.ic_square_share)
            MIcon(iconResId = R.drawable.ic_menu)
        }
    }
}

@Preview
@Composable
fun BottomBarPreview() {
    PlayerBottomBar()
}
