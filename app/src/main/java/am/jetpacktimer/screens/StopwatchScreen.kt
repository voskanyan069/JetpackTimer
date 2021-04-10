package am.jetpacktimer.screens

import am.jetpacktimer.components.Stopwatch
import am.jetpacktimer.views.TimerButton
import android.util.Log
import androidx.compose.animation.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

private lateinit var stopwatchState: MutableState<Int>
private lateinit var startButtonVisibilityState: MutableState<Boolean>
private val stopwatch = object : Stopwatch(1000L) {
    override fun onTick(millisElapsed: Long) {
        stopwatchState.value++
    }
}

@ExperimentalAnimationApi
@Composable
fun StopwatchScreen() {
    stopwatchState = remember { mutableStateOf(0) }
    startButtonVisibilityState = remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "${stopwatchState.value}",
            style = MaterialTheme.typography.h2,
        )
        AnimatedVisibility(
            visible = startButtonVisibilityState.value,
            enter = slideInVertically(
                initialOffsetY = { -40 }
            ) + expandVertically(
                expandFrom = Alignment.Top
            ) + fadeIn(initialAlpha = 0.3f),
            exit = slideOutVertically() + shrinkVertically() + fadeOut()
        ) {
            TimerButton {
                Log.d("mTag", "Stopwatch button clicked")
                stopwatch.start()
                startButtonVisibilityState.value = false
            }
        }
    }
}

@Composable
fun StopwatchButton(title: String, onClick: () -> Unit) {
    Button(onClick = { onClick() }) {
        Text(text = title)
    }
}