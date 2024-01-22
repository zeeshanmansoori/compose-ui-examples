package com.zee.compose_ui_examples.ui.player.component

import android.content.Context.AUDIO_SERVICE
import android.media.AudioManager
import android.media.AudioManager.AudioPlaybackCallback
import android.media.AudioPlaybackConfiguration
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.zee.compose_ui_examples.R
import com.zee.compose_ui_examples.ui.MainActivity
import com.zee.compose_ui_examples.ui.custom.MIcon
import com.zee.compose_ui_examples.ui.custom.VolumeSlider

@Composable
fun VolumeSliderWithAlbum(modifier: Modifier = Modifier) {

    val padding = 20.dp
    val strokeWidth = 10.dp
    val imagePadding = 10.dp

    val context = LocalContext.current

    val audioManager = context.getSystemService(AUDIO_SERVICE) as AudioManager

    // on below line we are creating variables for
    // volume level, max volume, volume percent.
    val volumeLevel = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
    val maxVolumeLevel = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
    val volumePercent = (volumeLevel.toFloat() / maxVolumeLevel * 100).toInt()




    Box(modifier = modifier.fillMaxWidth()) {

        Box(
            modifier = Modifier
                .wrapContentSize()
                .padding(
                    start = imagePadding + padding + strokeWidth * 2,
                    top = imagePadding + padding,
                    end = imagePadding + padding + strokeWidth * 2
                )
                .shadow(15.dp, shape = CircleShape, spotColor = MaterialTheme.colorScheme.primary)
                .clip(CircleShape)

        ) {
            Image(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(CircleShape),
                painter = painterResource(id = R.drawable.temp_music),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
        }

        Column(modifier = Modifier.fillMaxWidth()) {

            VolumeSlider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(start = padding, end = padding),
                progress = volumePercent,
                strokeWidth = strokeWidth,
                progressTrackColors = MainActivity.gradientColors()
            ){
                val max = maxVolumeLevel * it /100
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,max,AudioManager.FLAG_PLAY_SOUND)
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                MIcon(
                    modifier = Modifier,
                    iconResId = R.drawable.volume_icon,
                )


                MIcon(
                    modifier = Modifier,
                    iconResId = R.drawable.volume_loud_icon
                )
            }
        }

    }
}