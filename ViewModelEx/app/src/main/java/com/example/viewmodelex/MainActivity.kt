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

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        initViews()
        showDialogAfter3seconds()
    }

    private fun initViews() {
        binding.increaseButton.setOnClickListener {
            viewModel.increaseCount()
        }

        viewModel.count.observe(this) {
            binding.countText.text = it.toString()
        }

        viewModel.showDialog.observe(this) {
            if (it) {
                showDialogAfter3seconds()
            }
        }
    }

    private fun showDialogAfter3seconds() {
        AlertDialog.Builder(this)
            .setMessage("3 seconds passed")
            .show()

    }
}