package com.example.revcomposetutorial

import androidx.compose.ui.graphics.painter.Painter

data class listdata(
    val id: Int,
    val fruit: String,
    val price: String,
    val status: String,
    val date: String,
    val imgSrc: Painter
)
