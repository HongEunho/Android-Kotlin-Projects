package com.example.secretdiary

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.EditText
import androidx.core.content.edit
import androidx.core.widget.addTextChangedListener

class DiaryActivity : AppCompatActivity() {

    private lateinit var diaryEditText: EditText

    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diary)

        diaryEditText = findViewById(R.id.diaryEditText)

        val detailPreferences = getSharedPreferences("diary", Context.MODE_PRIVATE)

        diaryEditText.setText(detailPreferences.getString("detail",""))

        val runnable = Runnable {
            detailPreferences.edit{
                putString("detail", diaryEditText.text.toString())
            }

            Log.d("DiaryActivity","SAVE!!! ${diaryEditText.text.toString()}")
        }

        diaryEditText.addTextChangedListener {
            Log.d("DiaryActivity","TextChanged :: $it")
            handler.removeCallbacks(runnable)
            handler.postDelayed(runnable, 500)
        }
    }
}