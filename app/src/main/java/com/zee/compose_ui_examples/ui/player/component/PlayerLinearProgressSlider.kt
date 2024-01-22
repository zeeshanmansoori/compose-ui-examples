package com.zee.compose_ui_examples.ui.player.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderPositions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zee.compose_ui_examples.ui.custom.MThumb
import com.zee.compose_ui_examples.ui.theme.orangeColor
import com.zee.compose_ui_examples.ui.theme.pinkColor
import com.zee.compose_ui_examples.ui.theme.trackColor
import com.zee.compose_ui_examples.utils.Constants
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerLinearProgressSlider(
    modifier: Modifier = Modifier,
    audioDuration: Int,
    colors: List<Color>
) {

    var currentPosition by remember {
        mutableStateOf(0)
    }


    LaunchedEffect(key1 = true) {
        while (currentPosition<=audioDuration){
            delay(1000)
            currentPosition += 1
        }
    }

    Column(modifier.fillMaxWidth()) {

        val sliderTrack: @Composable (SliderPositions) -> Unit = { slidePosition ->
            val progress = slidePosition.positionFraction

            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Constants.TRACK_HEIGHT.dp)
            ) {


                val strokeWidth = 10.dp.toPx()
                val yOffset = size.height - strokeWidth / 2f
                val start = Offset.Zero.copy(y = yOffset)
                val trackEnd = Offset(size.width, yOffset)
                val progressEnd = Offset(size.width * progress, yOffset)

                drawLine(
                    color = trackColor,
                    start = start,
                    end = trackEnd,
                    cap = StrokeCap.Round,
                    strokeWidth = strokeWidth,
                )

                drawLine(
                    brush = Brush.linearGradient(colors),
                    start = start,
                    end = progressEnd,
                    cap = StrokeCap.Round,
                    strokeWidth = strokeWidth,
                )


            }
        }

        val interactionSource = remember {
            MutableInteractionSource()
        }

        Slider(
            modifier = Modifier.fillMaxWidth(),
            value = currentPosition / audioDuration.toFloat(),
            onValueChange = {
                currentPosition = (audioDuration * it).roundToInt()
            },
            track = sliderTrack,
            enabled = true,
            interactionSource = interactionSource,
            thumb = { MThumb(interactionSource = interactionSource) },
        )


        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = currentPosition.toPlayerTimeStamp(), style = TextStyle(
                    fontWeight = FontWeight.Normal, fontSize = 14.sp
                )
            )
            Text(
                text = audioDuration.toPlayerTimeStamp(), style = TextStyle(
                    fontWeight = FontWeight.Normal, fontSize = 14.sp
                )
            )
        }
    }
}

fun Int.toPlayerTimeStamp(): String {
    val min = this / 60
    val sec = this % 60

    val minText = if (min < 10) "0$min" else min.toString()
    val secText = if (sec < 10) "0$sec" else sec.toString()

    return "$minText:$secText"
}


@Preview
@Composable
fun PlayerLinearProgressBarPreview() {
    PlayerLinearProgressSlider(
        colors = listOf(pinkColor, orangeColor),
        audioDuration = 40000
    )
}