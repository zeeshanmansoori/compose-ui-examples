package com.zee.compose_ui_examples.ui.statusProgress

import androidx.compose.runtime.asFloatState
import androidx.compose.runtime.mutableFloatStateOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class StatusViewModel {
    private val _state = mutableFloatStateOf(0f)
    val state = _state.asFloatState()
    private val scope = CoroutineScope(Dispatchers.IO)
    private var job: Job? = null

    fun startProgress() {
        job?.cancel()

        job = scope.launch {
            var progress = 0f
            while (isActive && progress <= 1f) {
                val delayTime = if (progress < .3) 100L else if (progress > .7) 10L else  40L
                delay(delayTime)
                progress += .01f
                _state.floatValue = progress

            }
        }
    }

    companion object {
        private var instance: StatusViewModel? = null
        fun getInstance(): StatusViewModel {
            if (instance == null) instance = StatusViewModel()

            return instance!!

        }
    }

}