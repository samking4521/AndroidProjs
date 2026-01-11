package com.example.androidfundamentals.utils

import com.example.androidfundamentals.R

class ItemsArray {
    fun getItemData(): MutableList<listdata>{
        val data = mutableListOf(
            listdata(
                1,
                 "Apple chops",
                "$52.25",
                "Processing",
                "24th Jan, 2025",
                R.drawable.appleimg
            ),
            listdata(
                2,
                "Pawpaw flakes",
                "$22.10",
                "Delivered",
                "31th July, 2025",
                R.drawable.pawpaw
            ),
            listdata(
                3,
                "Pear Sauce",
                "$12.25",
                "Processing",
                "10th Jan, 2025",
                R.drawable.pear
            ),
            listdata(
                4,
                "Orange juice",
                "$32.25",
                "Delivered",
                "1st May, 2025",
                R.drawable.orange
            ),
            listdata(
                5,
                "Raspberry",
                "$28.25",
                "Processing",
                "24th Jan, 2025",
                R.drawable.raspberry
            ),
            listdata(
                6,
                "Pineapple flakes",
                "$19.10",
                "Delivered",
                "24th Jan, 2025",
                R.drawable.pineapple
            ),
            listdata(
                7,
                "Strawberry",
                "$10.25",
                "Processing",
                "24th Jan, 2025",
                R.drawable.strawberry
            ),
            listdata(
                8,
                "Guava",
                "$52.25",
                "Processing",
                "24th Jan, 2025",
                R.drawable.guava
            ),
        )
        return data
    }
}