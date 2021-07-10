package com.example.githubrepositoryapp.data.database

import android.content.Context
import androidx.room.Room

object DataBaseProvider {

    private const val DB_NAME = "github_repository_app.db"

    fun provideDB(context: Context) = Room.databaseBuilder(
        context, SimpleGithubDatabase::class.java, DB_NAME
    ).build()
}