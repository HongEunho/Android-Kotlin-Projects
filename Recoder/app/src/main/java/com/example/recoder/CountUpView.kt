package com.example.recoder

import android.content.Context
import android.os.SystemClock
import android.util.AttributeSet
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView

class CountUpView(context: Context, attributeSet: AttributeSet?= null) : AppCompatTextView(context, attributeSet) {

    private var startTimeStamp : Long = 0L

    private var countUpAction: Runnable = object: Runnable {
        override fun run() {
            val currentTimeStamp = SystemClock.elapsedRealtime()

            val countTimeSeconds = ((currentTimeStamp - startTimeStamp) / 1000L).toInt()
            updateCountTime(countTimeSeconds)

            handler.postDelayed(this, 1000)
        }

    }

    fun startCountUp() {
        startTimeStamp = SystemClock.elapsedRealtime()
        handler?.post(countUpAction)
    }

    fun stopCountUp() {
        handler?.removeCallbacks(countUpAction)
    }

    fun clearCountUp() {
        updateCountTime(0)
    }

    private fun updateCountTime(countTimeSeconds: Int){
        val minutes = countTimeSeconds / 60
        val seconds = countTimeSeconds % 60

        text = "%02d:%02d".format(minutes, seconds)
    }
}