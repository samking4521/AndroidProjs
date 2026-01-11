package com.example.merecyclerview.Adaptor

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.merecyclerview.Utils.ListItems
import com.example.merecyclerview.R
import com.example.merecyclerview.SecondActivity
import com.example.merecyclerview.Utils.Constants

class ListAdaptor(val context: Context, private val listItems: MutableList<ListItems>): RecyclerView.Adapter<ListAdaptor.ListViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
         val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_list_view, parent, false )
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
            val viewPosition = listItems[position]
            holder.title.text = viewPosition.title
            holder.desc.text = viewPosition.description
    }

    inner class ListViewHolder(view: View):RecyclerView.ViewHolder(view){
            val title: TextView = view.findViewById(R.id.textView1)
            val desc: TextView = view.findViewById(R.id.desc1)

            init {
                view.setOnClickListener {
                    val intent = Intent(context, SecondActivity::class.java).also {
                         it.putExtra(Constants.titleVal, title.text)
                        it.putExtra(Constants.descVal, desc.text)
                        context.startActivity(it)
                    }

                }
            }
    }
}