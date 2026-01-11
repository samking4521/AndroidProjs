package com.example.androidfundamentals

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidfundamentals.utils.listdata

class Adapter(private val data: MutableList<listdata>): RecyclerView.Adapter<Adapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
       val productName: TextView = view.findViewById(R.id.fruit_name)
        val productPrice: TextView = view.findViewById(R.id.price)
        val productStatus: TextView = view.findViewById(R.id.status)
        val productDate: TextView = view.findViewById(R.id.date)
        val productImage: ImageView = view.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
         return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         holder.productName.text = data[position].fruit
        holder.productPrice.text = data[position].price
        holder.productStatus.text = data[position].status
        holder.productDate.text = data[position].date
        holder.productImage.setImageResource(data[position].imgSrc)
    }
}