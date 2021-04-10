package am.jetpacktimer.components

import android.os.CountDownTimer

abstract class Stopwatch(countUpInterval: Long) {
    private val countDownTimer: CountDownTimer
    private var countDownCycle: Int? = null

    init {
        countDownTimer = object : CountDownTimer(Long.MAX_VALUE, countUpInterval) {
            override fun onTick(millisUntilFinished: Long) {
                this@Stopwatch.onTick(Long.MAX_VALUE * countDownCycle!! - millisUntilFinished)
            }

            override fun onFinish() {
                countDownCycle = countDownCycle?.plus(1)
                this@Stopwatch.start()
            }
        }
        countDownCycle = 1
    }

    fun start() {
        countDownTimer.start()
    }

    fun stop() {
        countDownTimer.cancel()
    }

    fun cancel() {
        stop()
        countDownCycle = 1
    }

    abstract fun onTick(millisElapsed: Long)
}