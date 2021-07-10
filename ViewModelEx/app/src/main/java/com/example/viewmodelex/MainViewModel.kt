package com.example.viewmodelex

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class MainViewModel: ViewModel() {
    private val _count = MutableLiveData<Long>().apply {
        value = 0
    }

    val count: LiveData<Long>
        get() = _count

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean>
        get() = _showDialog

    private val executor = Executors.newSingleThreadScheduledExecutor()

    init {
        executor.schedule({
            _showDialog.postValue(true)
        }, 3, TimeUnit.SECONDS)
    }

    fun increaseCount() {
        _count.value = (count.value ?: 0) + 1

    }
}