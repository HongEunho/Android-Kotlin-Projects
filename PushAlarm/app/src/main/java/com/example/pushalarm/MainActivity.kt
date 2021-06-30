package com.example.pushalarm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private val resultTextView: TextView by lazy{
        findViewById<TextView>(R.id.resultTextView)
    }

    private val firebaseTokenTextView: TextView by lazy{
        findViewById<TextView>(R.id.firebaseTokenTextView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}