package com.example.revcomposetutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.revcomposetutorial.ui.theme.RevComposeTutorialTheme
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
//            val theArray = ItemsArray().getItemData()
            // Rev 1
//             Column (modifier = Modifier
//                 .background(Color.Cyan)
//                 .fillMaxSize()
//                 .border(2.dp, Color.Red, RectangleShape)
//                 .padding(20.dp)
//                 ){
//                 Text("Hello world",
//                     textAlign = TextAlign.Center,
//                     modifier = Modifier
//                         .padding(bottom = 10.dp)
//                         )
//                 Text("This is a compose program, cool right!")
//                 val src = painterResource(id = R.drawable.marc)
//                 val desc = "Marc rousavy"
//                 val title = "Marc rousavy"
//
//                 ImageDisplay(
//                     src,
//                     desc,
//                     title,
//                     modifier = Modifier
//                         .fillMaxWidth(0.5f)
//
//                 )
//
//                 Row(modifier = Modifier.
//                     padding(top = 20.dp)
//                 ) {
//                     val color = remember {
//                         mutableStateOf(Color.Blue)
//                     }
//
//                     ColorBox(modifier = Modifier.fillMaxSize()
//                        .weight(1f)){
//                         color.value = it
//                     }
//                     Box(
//                         modifier = Modifier
//                             .fillMaxSize()
//                             .weight(1f)
//                             .background(color.value)
//                     )
//                 }
//
//             }
//
            // Rev 2
//            var text by remember { mutableStateOf("") }
//            val snackbarHostState = remember { SnackbarHostState() }
//            val scope = rememberCoroutineScope()
//
//            Scaffold(
//                modifier = Modifier.fillMaxSize(),
//                snackbarHost = {
//                    SnackbarHost(snackbarHostState)
//                }
//            ) {
//                Column(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(it)
//                        .padding(10.dp),
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    verticalArrangement = Arrangement.Center
//
//                ) {
//                    TextField(
//                        value = text,
//                        onValueChange = { newText ->
//                            text = newText
//                        },
//                        label = {
//                            (Text("Enter your name"))
//                        },
//                        singleLine = true,
//                        modifier = Modifier.fillMaxWidth()
//
//                    )
//                    Spacer(
//                        modifier = Modifier
//                            .height(20.dp)
//                    )
//                    Button(
//                        onClick = {
//                            scope.launch {
//                                snackbarHostState.showSnackbar(
//                                  text
//
//                                )
//                            }
//                        },
//                        modifier = Modifier
//                            .align(Alignment.End)
//                    ) {
//                        Text("Show snack bar")
//                    }
//                }
//            }



//             LazyColumn(
//                 modifier =
//                     Modifier.fillMaxSize().
//                 background(Color.White)
//             ) {
//
//                items(theArray){
//                    Row(
//                        modifier = Modifier.
//                        fillMaxWidth().
//                        height(100.dp).
//                        padding(10.dp)
//
//                    ) {
//                        Image(it.imgSrc, "myImg",
//                            modifier = Modifier
//                                .width(100.dp)
//                                .height(100.dp)
//                            ,
//                            contentScale = ContentScale.Crop
//                                )
//                        Spacer(modifier =
//                          Modifier.width(10.dp)
//                              .height(80.dp))
//                        Column(modifier =
//                        Modifier
//                            .fillMaxSize(),
//                            verticalArrangement = Arrangement.SpaceBetween
//                            ){
//                            Text(it.fruit)
//                            Text(it.price, color = Color.Gray)
//                            Row (
//                                modifier = Modifier.fillMaxWidth(),
//                                horizontalArrangement = Arrangement.SpaceBetween
//                            ){
//                                Text(it.status, color = Color.LightGray)
//                                Text(it.date, color = Color.LightGray)
//                            }
//
//                        }
//
//                    }
//                    Spacer (
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(2.dp)
//                            .background(Color.LightGray)
//                    )
//
//                }
//             }
            Navigation()
        }

    }
}
// Rev 1
//@Composable
//fun ImageDisplay(
//    src: Painter,
//    desc: String,
//    title: String,
//    modifier: Modifier
//){
//    Card(
//        modifier = modifier
//            .padding(top = 10.dp)
//            ,
//        elevation = CardDefaults.cardElevation(
//            defaultElevation = 10.dp
//        )
//
//    ) {
//        Box (
//            modifier = Modifier
//
//                .height(300.dp)
//        ){
//            Image(
//                src,
//                desc,
//                contentScale = ContentScale.Crop,
//                modifier = Modifier
//                    .fillMaxSize()
//            )
//
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(
//                        brush = Brush.verticalGradient(
//                            colors = listOf(
//                                Color.Transparent,    // Top color
//                                Color.Black  // Bottom color
//                            ),
//                            startY = 300f
//                        )
//                    )
//            )
//
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(bottom = 10.dp),
//                    contentAlignment = Alignment.BottomCenter
//            ) {
//                Text(title, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color.White)
//            }
//
//        }
//
//    }
//}
//
//@Composable
//fun ColorBox(
//    modifier: Modifier,
//    updateColor: (Color) -> Unit
//){
//
//    Box(
//        modifier = modifier
//
//            .background(Color.Red)
//            .clickable {
//                updateColor(Color(
//                    Random.nextFloat(),
//                    Random.nextFloat(),
//                    Random.nextFloat(),
//                    1f
//                ))
//            }
//    )
//}
