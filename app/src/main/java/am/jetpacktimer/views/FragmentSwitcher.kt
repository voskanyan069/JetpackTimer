package am.jetpacktimer.views

import am.jetpacktimer.MainActivity
import am.jetpacktimer.states.SelectedFragment
import am.jetpacktimer.ui.theme.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private lateinit var selectedFragment: MutableState<SelectedFragment>

@Composable
fun FragmentSwitcher() {
    selectedFragment = remember { mutableStateOf(SelectedFragment.STOPWATCH) }

    Row(
        modifier = Modifier
            .height(40.dp)
            .width(240.dp)
            .border(border = BorderStroke(1.dp, fragmentSwitcherBorderColor), shape = RoundedCornerShape(50)),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        SwitcherContainer(isSelected = selectedFragment.value == SelectedFragment.STOPWATCH, topStart = 50, topEnd = 0, bottomStart = 50, bottomEnd = 0, content = {
            SwitcherText(isSelected = selectedFragment.value == SelectedFragment.STOPWATCH, title = "Stopwatch")
        })
        SwitcherContainer(isSelected = selectedFragment.value == SelectedFragment.TIMER, topStart = 0, topEnd = 50, bottomStart = 0, bottomEnd = 50, content = {
            SwitcherText(isSelected = selectedFragment.value == SelectedFragment.TIMER, title = "Timer")
        })
    }
}

@Composable
private fun SwitcherContainer(content: @Composable () -> Unit, isSelected: Boolean,
                              topStart: Int, topEnd: Int, bottomStart: Int, bottomEnd: Int) {
    Box(
        modifier = Modifier
            .width(120.dp)
            .fillMaxHeight()
            .clip(RoundedCornerShape(topStart, topEnd, bottomEnd, bottomStart))
            .background(
                color = if (isSelected) {
                    fragmentSwitcherSelectedContainerColor
                } else {
                    fragmentSwitcherNonSelectedContainerColor
                }
            )
            .clickable(onClick = {
                if (!isSelected) {
                    selectedFragment.value =
                        if (selectedFragment.value == SelectedFragment.STOPWATCH) {
                            SelectedFragment.TIMER
                        } else {
                            SelectedFragment.STOPWATCH
                        }
                    MainActivity.changeScreen(selectedFragment.value)
                }
            })
    ) {
        content()
    }
}

@Composable
private fun SwitcherText(isSelected: Boolean = false, title: String) {
    Text(
        text = title,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(0.dp, 8.dp, 0.dp, 0.dp),
        textAlign = TextAlign.Center,
        color = if (isSelected) {
            fragmentSwitcherSelectedTextColor
        } else {
            fragmentSwitcherNonSelectedTextColor
        }
    )
}

@Preview
@Composable
fun FragmentSwitcherPreview() {
    FragmentSwitcher()
}