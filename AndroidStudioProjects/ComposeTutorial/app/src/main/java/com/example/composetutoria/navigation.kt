package com.example.composetutoria

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Navigation(){
    // get access to the controller
    val navController = rememberNavController()
    // host the controller
    NavHost(navController = navController, startDestination = Screen.MainScreen.route){
            composable(route = Screen.MainScreen.route){
                MainScreen(navController = navController)
            }
        // /?name={name}
            composable(route = Screen.DetailScreen.route + "/{name}",
                listOf(
                    navArgument("name"){
                        type = NavType.StringType
                        defaultValue = "User"
                        nullable = true
                    }
                )
            ){
                backEntry ->
                backEntry.arguments?.getString("name")?.let { DetailScreen(name = it) }
            }
    }
}

@Composable
fun MainScreen(navController: NavController){
    var text by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp),
        verticalArrangement = Arrangement.Center
    ) {
            TextField(
                value = text,
                onValueChange = {  text = it },
                label = { Text("Enter something") },
                modifier = Modifier.fillMaxWidth()
            )
        Spacer(modifier = Modifier
            .height(10.dp))
        Button(
            onClick = {
                 navController.navigate(Screen.DetailScreen.withArgs(text))
            },
            modifier = Modifier
                .align(Alignment.End)

        ) {
            Text("Go to details screen", color = Color.White)
        }
    }


}

@Composable
fun DetailScreen(name: String){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Green)

    ) {
        Text(text = "Hello $name")
    }
}

