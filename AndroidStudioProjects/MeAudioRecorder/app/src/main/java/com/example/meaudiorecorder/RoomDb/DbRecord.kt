package com.example.meaudiorecorder.RoomDb

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "AudioRecord")
data class DbRecord(
    var filePath: String,
    var fileName: String,
    var duration: String,
    var date: String
) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
   @Ignore var checked = false
}