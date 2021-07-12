package com.example.viewmodelex

import androidx.lifecycle.*
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class MainViewModel(handle: SavedStateHandle): ViewModel() {
    private val _count = handle.getLiveData<Long>(KEY_COUNT, 0)

    val count: LiveData<Long>
        get() = _count

    val timesText = Transformations.map(count) {
        "$it x 2 = ${it * 2}"
    }

    fun increaseCount() {
        _count.value = (count.value ?: 0) + 1

    }

    companion object {
        private const val KEY_COUNT = "count"
    }
}