package com.example.recoder

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.random.Random

class SoundVisualizerView(context: Context, attrs: AttributeSet?= null) : View(context, attrs) {

    var onRequestCurrentAmplitude: (() ->Int)? = null
    private val amplitudePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply{
        color = context.getColor(R.color.purple_500)
        strokeWidth = LINE_WIDTH
        strokeCap = Paint.Cap.ROUND

    }
    private var drawingWidth : Int = 0
    private var drawingHeight : Int = 0
    private var drawingAmplitudes : List<Int> = emptyList()
    private var isReplaying: Boolean = false
    private var replayingPosition: Int = 0

    private var visualizeRepeatAction: Runnable = object : Runnable{
        override fun run() {
            if(!isReplaying) {
                var currentAmplitude = onRequestCurrentAmplitude?.invoke() ?: 0
                drawingAmplitudes = listOf(currentAmplitude) + drawingAmplitudes
            }
            else{
                replayingPosition++
            }
            invalidate()
            handler.postDelayed(this, ACTION_INTERVAL)
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        drawingWidth = w
        drawingHeight = h
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas ?: return

        val centerY = drawingHeight / 2f
        var offsetX = drawingWidth.toFloat() // 오른쪽 부터 그릴거기 때문에 맨 오른쪽부터 그리도록 설정

        drawingAmplitudes
            .let{
                if(isReplaying){
                    it.takeLast(replayingPosition)
                }
                else{
                    it
                }
            }
            .forEach {
            val lineLength = it / MAX_AMPLITUDE * drawingHeight * 0.8F

            offsetX -= LINE_SPACE
            if(offsetX < 0) return@forEach

            canvas.drawLine(offsetX,centerY - lineLength / 2F, offsetX, centerY + lineLength / 2F,
            amplitudePaint)
        }
    }

    fun startVisualizing(isReplaying : Boolean) {
        this.isReplaying = isReplaying
        handler?.post(visualizeRepeatAction)
    }

    fun stopVisualizing() {
        replayingPosition = 0
        handler?.removeCallbacks(visualizeRepeatAction)
    }

    fun clearVisualizing() {
        drawingAmplitudes = emptyList()
        invalidate()
    }

    companion object{
        private const val LINE_WIDTH = 10F
        private const val LINE_SPACE = 15F
        private const val MAX_AMPLITUDE = Short.MAX_VALUE.toFloat()
        private const val ACTION_INTERVAL = 20L
    }
}