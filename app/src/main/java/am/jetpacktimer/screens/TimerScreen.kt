package am.jetpacktimer.screens

import am.jetpacktimer.views.TimerButton
import android.os.CountDownTimer
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun TimerScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TimerButton {
            Log.d("mTag", "Timer button clicked")
            val timer = object : CountDownTimer(10000L, 1000L) {
                override fun onTick(millisUntilFinished: Long) {
                    Log.d("mTag", "Until end - $millisUntilFinished")
                }

                override fun onFinish() {
                    Log.d("mTag", "Timer finished")
                }
            }
            timer.start()
        }
    }
}