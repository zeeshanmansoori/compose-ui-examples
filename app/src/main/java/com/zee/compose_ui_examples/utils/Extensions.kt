package com.zee.compose_ui_examples.utils

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.input.pointer.pointerInput


fun Modifier.onTapWithResizeAnimation(onTap: () -> Unit) = composed {

    var isButtonTouchMode by remember {
        mutableStateOf(false)
    }

    val scaleFactor = animateFloatAsState(
        targetValue = if (isButtonTouchMode) .85f else 1f,
        label = ""
    )

    scale(scaleFactor.value).pointerInput(true) {
        detectTapGestures(onPress = {
            isButtonTouchMode = true
            tryAwaitRelease()
            isButtonTouchMode = false
        }, onTap = {
            onTap.invoke()
        })
    }


}

fun Modifier.animateVisibility(
    controllerVisibilityState: MutableState<Boolean>,
) = composed {


    val alphaAsState = animateFloatAsState(
        targetValue = if (controllerVisibilityState.value) 1f else 0f,
        label = ""
    )

    alpha(alphaAsState.value)

}

fun Modifier.onTap(onTap: () -> Unit): Modifier {
    return pointerInput(true) {
        detectTapGestures(onTap = {
            onTap.invoke()
        })
    }
}