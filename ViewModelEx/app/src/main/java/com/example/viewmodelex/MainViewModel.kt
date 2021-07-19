package com.example.viewmodelex

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.*
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

    init {
        viewModelScope.launch {
            delay(3000)
            while(true){
                delay(1000)
                increaseCount()
            }
        }
    }

    suspend fun increaseCount() = withContext(Dispatchers.Main.immediate) {
        _count.value = (count.value ?: 0) + 1
        Log.d("count", _count.value.toString())
    }


    companion object {
        private const val KEY_COUNT = "count"
    }
}