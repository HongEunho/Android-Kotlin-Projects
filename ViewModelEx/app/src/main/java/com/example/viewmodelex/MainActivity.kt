package com.example.viewmodelex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.view.postDelayed
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodelex.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        setContentView(binding.root)
        initViews()

    }

    private fun initViews() {
        binding.increaseButton.setOnClickListener {
            viewModel.increaseCount()
        }

    }

}