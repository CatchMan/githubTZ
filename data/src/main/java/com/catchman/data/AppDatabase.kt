package com.example.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.catchman.data.github.storage.GitHubDao
import com.catchman.data.github.storage.StorageUser


@Database(entities = [(StorageUser::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getTodoDao(): GitHubDao


}