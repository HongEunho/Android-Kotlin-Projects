package com.example.contactappmvvm.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.contactappmvvm.data.Contact

@Database(entities = [Contact::class], version = 1)
abstract class ContactDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao

    companion object {
        private var INSTANCE: ContactDatabase? = null
        private val lock = Any()

        fun getInstance(context: Context): ContactDatabase? {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        ContactDatabase::class.java, "Contact.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
                return INSTANCE
            }

        }

    }
}