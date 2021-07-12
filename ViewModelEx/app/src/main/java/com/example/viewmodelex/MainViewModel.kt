package com.example.viewmodelex

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class MainViewModel(initCount: Long): ViewModel() {
    private val _count = MutableLiveData<Long>().apply {
        value = initCount
    }

    val count: LiveData<Long>
        get() = _count

    val timesText = Transformations.map(count) {
        "$it x 2 = ${it * 2}"
    }

    fun increaseCount() {
        _count.value = (count.value ?: 0) + 1

    }
}