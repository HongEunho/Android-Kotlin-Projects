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
        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(savedInstanceState?.getLong(KEY_COUNT) ?: 0)
        )[MainViewModel::class.java]
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        initViews()

    }

    private fun initViews() {
        binding.increaseButton.setOnClickListener {
            viewModel.increaseCount()
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong(KEY_COUNT, viewModel.count.value ?: 0)
    }

    companion object{
        private const val KEY_COUNT = "count"
    }

}