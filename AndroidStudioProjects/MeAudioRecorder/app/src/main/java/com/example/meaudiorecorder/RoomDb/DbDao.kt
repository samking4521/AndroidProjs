package com.example.meaudiorecorder.RoomDb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DbDao {
    @Query("SELECT * FROM AudioRecord")
    suspend fun getAllRecords(): List<DbRecord>

    @Query("SELECT * FROM AudioRecord WHERE fileName LIKE :query")
    suspend fun getAudioRecordByName(query: String): List<DbRecord>

    @Insert
    suspend fun insertAudioRecord(audio: DbRecord)

    @Delete
    suspend fun deleteRecord(audio: DbRecord)

    @Update
    suspend fun updateRecord(audio: DbRecord)
}