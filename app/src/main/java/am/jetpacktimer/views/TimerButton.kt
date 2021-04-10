package am.jetpacktimer.views

import am.jetpacktimer.ui.theme.timerButtonColor
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun TimerButton(onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        modifier = Modifier
            .size(60.dp)
            .clip(shape = CircleShape),
        colors = ButtonDefaults.buttonColors(backgroundColor = timerButtonColor),
    ) {}
}