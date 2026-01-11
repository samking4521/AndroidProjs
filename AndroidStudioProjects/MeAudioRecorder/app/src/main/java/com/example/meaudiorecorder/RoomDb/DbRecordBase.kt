package com.example.meaudiorecorder.RoomDb

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DbRecord::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun audioDao(): DbDao
}