package com.example.meaudiorecorder

import com.example.meaudiorecorder.RoomDb.DbRecord

interface ListClickListeners {
    fun onClick(position: Int)
    fun onLongClick(position: Int): Boolean
}