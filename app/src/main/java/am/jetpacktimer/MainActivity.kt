package am.jetpacktimer

import am.jetpacktimer.screens.StopwatchScreen
import am.jetpacktimer.screens.TimerScreen
import am.jetpacktimer.states.Global
import am.jetpacktimer.states.SelectedFragment
import am.jetpacktimer.ui.theme.JetpackTimerTheme
import am.jetpacktimer.views.FragmentSwitcher
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    companion object {
        private lateinit var selectedFragment: MutableState<SelectedFragment>

        fun changeScreen(newScreen: SelectedFragment) {
            selectedFragment.value = newScreen
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                statusBarColor = if (Global.isDarkTheme) {
                    getColor(R.color.darkStatusBar)
                } else {
                    getColor(R.color.lightStatusBar)
                }
            }
        }

        setContent {
            selectedFragment = remember { mutableStateOf(SelectedFragment.STOPWATCH) }
            JetpackTimerTheme(darkTheme = true) {
                Surface {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(40.dp))
                        FragmentSwitcher()
                        Spacer(modifier = Modifier.height(40.dp))
                        when (selectedFragment.value) {
                            SelectedFragment.TIMER -> TimerScreen()
                            SelectedFragment.STOPWATCH -> StopwatchScreen()
                        }
                    }
                }
            }
        }
    }

    @Preview
    @Composable
    fun DefaultPreview() {
        JetpackTimerTheme(darkTheme = true) {
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(40.dp))
                    FragmentSwitcher()
                    Spacer(modifier = Modifier.height(40.dp))
                    when(selectedFragment.value) {
                        SelectedFragment.TIMER -> TimerScreen()
                        SelectedFragment.STOPWATCH -> StopwatchScreen()
                    }
                }
            }
        }
    }
}
