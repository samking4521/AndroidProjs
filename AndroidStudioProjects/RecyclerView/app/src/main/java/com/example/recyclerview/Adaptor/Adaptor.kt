package com.example.recyclerview.Adaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.Model.ExampleItem
import com.example.recyclerview.R

class ExampleAdaptor(val context: Context, val elements: MutableList<ExampleItem>): RecyclerView.Adapter<ExampleAdaptor.ExampleViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false )
        return ExampleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return elements.size
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem = elements[position]
        holder.title.text = currentItem.title
        holder.description.text = currentItem.description
    }

    inner class ExampleViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.title)
        val description: TextView = view.findViewById(R.id.description)

        init {
            view.setOnClickListener {
                val position = adapterPosition
                val item = elements[position]
                Toast.makeText(context, item.title, Toast.LENGTH_SHORT).show()
            }
        }
    }
}