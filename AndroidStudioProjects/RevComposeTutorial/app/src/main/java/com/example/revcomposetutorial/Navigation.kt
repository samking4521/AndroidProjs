package com.example.revcomposetutorial

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.revcomposetutorial.ui_screens.DetailScreen
import com.example.revcomposetutorial.ui_screens.MainScreen

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route){
        composable(Screen.MainScreen.route){
                MainScreen(navController)
        }
        composable(Screen.DetailScreen.route + "/{name}/{age}",
           arguments =  listOf(
                navArgument("name") {
                    type = NavType.StringType
                    defaultValue = "User"
                },
                navArgument("age") {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ){
            backStackEntry ->
            val name = backStackEntry.arguments?.getString("name")
            val age = backStackEntry.arguments?.getInt("age")
            if (name != null) {
                if (age != null) {
                    DetailScreen(name, age)
                }
            }
        }
    }

}

