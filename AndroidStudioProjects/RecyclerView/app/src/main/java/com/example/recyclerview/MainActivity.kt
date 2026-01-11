package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.Adaptor.ExampleAdaptor
import com.example.recyclerview.Model.ExampleItem

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adaptor: ExampleAdaptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val exampleList = generateList(100)
        adaptor = ExampleAdaptor(this, exampleList)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = adaptor
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
    }

    private fun generateList(size: Int): MutableList<ExampleItem>{
        val list = mutableListOf<ExampleItem>()

        for(i in 0 until size){
            list.add(ExampleItem("Title $i", "Description $i"))
        }
        return list
    }
}