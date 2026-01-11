package com.example.meaudiorecorder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.meaudiorecorder.RoomDb.DbRecord

class Adapter( val recordData: ArrayList<DbRecord>, val listeners: ListClickListeners): RecyclerView.Adapter<Adapter.ViewHolder>() {
    private var editMode = false
    private var editState = false

    fun toggleEditMode(mode: Boolean){
             editMode = mode
    }

    fun toggleEditState(mode: Boolean){
        editState = mode
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val filename: TextView = view.findViewById(R.id.filename)
        val duration: TextView = view.findViewById(R.id.duration)
        val date: TextView = view.findViewById(R.id.date)
        val checkBox: CheckBox = view.findViewById(R.id.checkBox)

        init {
             view.setOnClickListener{
                 listeners.onClick(adapterPosition)
             }

            view.setOnLongClickListener {
                listeners.onLongClick(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return recordData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
          if(position != RecyclerView.NO_POSITION){
               val audioItem = recordData[position]
               holder.filename.text = audioItem.fileName
              holder.duration.text = audioItem.duration
              holder.date.text = audioItem.date
              holder.checkBox.isClickable = false
              if(editState){
                  if(audioItem.checked){
                      holder.checkBox.visibility = View.VISIBLE
                      holder.checkBox.isChecked = true
                  }
                  else{
                      holder.checkBox.visibility = View.VISIBLE
                      holder.checkBox.isChecked = false
                  }
              }else{
                  holder.checkBox.visibility = View.GONE
                  holder.checkBox.isChecked = false
              }


          }
    }
}