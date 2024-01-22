package com.zee.compose_ui_examples.ui.statusProgress

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zee.compose_ui_examples.R
import java.math.RoundingMode

@Composable
fun StatusProgressScreen() {

    val statusBarHeight = 48.dp + 48.dp

    val viewModel = remember {
        StatusViewModel.getInstance()
    }
    val progress by remember {
        viewModel.state
    }

    Column(modifier = Modifier.fillMaxSize()) {

        Surface(

            shadowElevation = 3.dp
        ) {
            Box(
                modifier = Modifier
                    .height(statusBarHeight)
                    .fillMaxWidth()
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(BottomCenter)
                        .padding(start = 10.dp, end = 10.dp, bottom = 5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_keyboard_arrow_down_24),
                            contentDescription = null,
                        )
                    }

                    val text =
                        if (progress != 1f) "downloading progress ${progress.toTwoDigitString()}" else "Done"
                    Text(text = text)

                    IconButton(onClick = {}) {

                        Icon(
                            modifier = Modifier.rotate(90f),
                            painter = painterResource(id = R.drawable.ic_menu),
                            contentDescription = null,
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(progress)
                        .background(MaterialTheme.colorScheme.primary.copy(.4f))
                )

            }
        }
//        Spacer(modifier = Modifier.weight(1f))
//        Button(modifier = Modifier
//            .padding(20.dp)
//            .align(CenterHorizontally), onClick = {
//            viewModel.startProgress()
//        }) {
//            Text(text = "Restart")
//        }


    }

    LaunchedEffect(key1 = true) {
//        WindowCompat.getInsetsController(current)

        viewModel.startProgress()
    }



}

private fun Float.toTwoDigitString(): String {
    return toBigDecimal().setScale(1, RoundingMode.DOWN).toDouble().toString()
}


@Preview
@Composable
fun StatusProgressPreview() {
    StatusProgressScreen()
}