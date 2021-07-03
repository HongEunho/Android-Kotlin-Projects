package com.example.recoder

import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    private val soundVisualizerView: SoundVisualizerView by lazy{
        findViewById(R.id.soundVisualizerView)
    }
    private val recordTimeTextView: CountUpView by lazy{
        findViewById(R.id.recordTimeTextView)
    }
    private val recordButton: RecordButton by lazy{
        findViewById<RecordButton>(R.id.recordButton)
    }
    private val resetButton: Button by lazy{
        findViewById<Button>(R.id.resetButton)
    }

    private val requiredPermissions = arrayOf(android.Manifest.permission.RECORD_AUDIO, android.Manifest.permission.READ_EXTERNAL_STORAGE)
    private var state = State.BEFORE_RECORDING
        set(value) {
            field = value
            resetButton.isEnabled = (value == State.AFTER_RECORDING) || (value == State.ON_PLAYING)
            recordButton.updateIconWithState(value)
        }

    private val recordingFilePath: String by lazy{
        "${externalCacheDir?.absolutePath}/recording.3gp"
    }
    private var recorder: MediaRecorder? = null
    private var player: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestAudioPermission()
        initViews()
        bindViews()
        initVariables()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode) {
            REQUEST_RECORD_AUDIO_PERMISSION -> {
                if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                }
                else{
                    finish()
                }
            }
            else -> {
                finish()
            }
        }

    }

    private fun requestAudioPermission() {
        requestPermissions(requiredPermissions, REQUEST_RECORD_AUDIO_PERMISSION)

    }

    private fun initViews() {
        recordButton.updateIconWithState(state)
    }

    private fun initVariables() {
        state = State.BEFORE_RECORDING
    }

    private fun bindViews() {
        soundVisualizerView.onRequestCurrentAmplitude = {
            recorder?.maxAmplitude ?: 0
        }
        resetButton.setOnClickListener {
            stopPlaying()
            state = State.BEFORE_RECORDING
            soundVisualizerView.clearVisualizing()
            recordTimeTextView.clearCountUp()
        }
        recordButton.setOnClickListener {
            when(state) {
                State.BEFORE_RECORDING -> {
                    startRecording()
                }
                State.ON_RECORDING -> {
                    stopRecording()
                }
                State.AFTER_RECORDING -> {
                    startPlaying()
                }
                State.ON_PLAYING -> {
                    stopPlaying()
                }
            }
        }
    }

    private fun startRecording() {
        recorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
            setOutputFile(recordingFilePath)
            prepare()
        }
        soundVisualizerView.startVisualizing(false)
        recordTimeTextView.startCountUp()
        recorder?.start()
        state = State.ON_RECORDING
    }

    private fun stopRecording() {
        recorder?.run{
            stop()
            release()
        }
        soundVisualizerView.stopVisualizing()
        recordTimeTextView.stopCountUp()
        recorder = null
        state = State.AFTER_RECORDING
    }

    private fun startPlaying() {
        player = MediaPlayer().apply {
            setDataSource(recordingFilePath)
            prepare()
            start()
        }
        player?.setOnCompletionListener {
            stopPlaying()
            state = State.AFTER_RECORDING
        }
        recordTimeTextView.startCountUp()
        soundVisualizerView.startVisualizing(true)
        state = State.ON_PLAYING
    }

    private fun stopPlaying() {
        player?.release()
        player = null
        state = State.AFTER_RECORDING
        soundVisualizerView.stopVisualizing()
        recordTimeTextView.stopCountUp()
    }

    companion object {
        private const val REQUEST_RECORD_AUDIO_PERMISSION = 201
    }
}