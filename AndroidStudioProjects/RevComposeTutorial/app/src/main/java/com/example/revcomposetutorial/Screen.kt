package com.example.revcomposetutorial

sealed class Screen(val route: String){
    object MainScreen: Screen("MainScreen")
    object DetailScreen: Screen("DetailScreen")
}