package com.example.merecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.merecyclerview.Adaptor.ListAdaptor
import com.example.merecyclerview.Utils.ListItems

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ListAdaptor
    private var listLength = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         val theList = createList(listLength)
        recyclerView = findViewById(R.id.recyclerView)
        adapter = ListAdaptor(this, theList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)


    }

    private fun createList(length: Int): MutableList<ListItems>{
        val list = mutableListOf<ListItems>()

        for(i in 0 until length){
            list.add(ListItems("Item $i", "Description $i"))
        }
        return list
    }
}