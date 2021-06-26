package com.example.calculatorwindows

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.calculatorwindows.dao.HistoryDao
import com.example.calculatorwindows.model.History

@Database(entities = [History::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao
}