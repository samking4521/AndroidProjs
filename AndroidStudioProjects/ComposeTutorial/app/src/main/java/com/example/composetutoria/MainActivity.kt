package com.example.composetutoria

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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.composetutoria.ui.theme.ComposeTutoriaTheme
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Specifying font families
//        val fontFamily = FontFamily(
//            Font(R.font.geologica_bold, FontWeight.Bold),
//            Font(R.font.geologica_extralight, FontWeight.Bold),
//            Font(R.font.geologica_light, FontWeight.Bold),
//            Font(R.font.geologica_medium, FontWeight.Bold),
//            Font(R.font.geologica_regular, FontWeight.Bold),
//            Font(R.font.geologica_semibold, FontWeight.Bold)
//        )

        setContent {


            // Rows
//            Row(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(Color.Green)
//                ,
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.SpaceEvenly
//            ) {
//                Text("Hello")
//                Text("Hello")
//                Text("Hello")
//            }


            // Columns
//            Column(
//                modifier = Modifier
//                    .background(Color.Green)
//
//                    .fillMaxHeight(0.5f)
//                    .fillMaxWidth(1.0f)
//                    .border(5.dp, Color.Blue)
//                    .padding(10.dp)
//                    .border(5.dp, Color.Cyan)
//                    .padding(5.dp)
//                    .border(5.dp, Color.Red)
//                    .padding(5.dp)
//            ) {
//                    Text("Hello", modifier = Modifier.border(5.dp, Color.Red).padding(20.dp))
//                    Spacer(modifier = Modifier.height(50.dp))
//                    Text("Alexandra")
//            }


            // Displaying Images
//            val painter = painterResource(R.drawable.mjj)
//            val contentDescription = "This is a photo with Alex"
//            val title = "This is Michael"
//            ImageCard(painter, contentDescription, title)

            // Annotated strings
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(Color(0xFF101010))
//            ) {
//                Text(text = buildAnnotatedString {
//                    withStyle(
//                        style = SpanStyle(
//                            color = Color.Green,
//                            fontSize = 55.sp
//                        ) ){
//                            append("J")
//                        }
//                    append("etpack")
//                    withStyle(
//                        style = SpanStyle(
//                            color = Color.Green,
//                            fontSize = 55.sp
//                        ) ){
//                        append(" C")
//                    }
//                    append("ompose")
//
//                },
//                    color = Color.White,
//                    fontSize = 30.sp,
//                    fontFamily = fontFamily,
//                    textDecoration = TextDecoration.LineThrough
//
//                )
//            }


//        Column{
//            val color = remember {
//                mutableStateOf(Color.Blue)
//            }
//
//            ColorBox(modifier = Modifier
//                .weight(1f)
//                .fillMaxSize()
//            ){
//                color.value = it
//            }
//            Box(modifier = Modifier
//                .weight(1f)
//                .background(color.value)
//                .fillMaxSize()
//
//            )
//        }

//

//            val snackbarHostState = remember { SnackbarHostState() }
//            var textFieldState by remember {
//                mutableStateOf("")
//            }
//            val scope = rememberCoroutineScope()
//            Scaffold(Modifier.fillMaxSize(), snackbarHost = {
//                SnackbarHost(snackbarHostState) { data ->
//                    Snackbar(
//                        modifier = Modifier
//                            .padding(12.dp)
//                    ) {
//                        Text(data.visuals.message)
//                    }
//                }
//            }) {
//                Column(
//                    modifier = Modifier
//                        .padding(it)
//                        .fillMaxSize(),
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    verticalArrangement = Arrangement.Center
//                ) {
//                    TextField(
//                        value = textFieldState,
//                        label = { Text("Enter your name") },
//                        onValueChange = {
//                            textFieldState = it
//                        },
//                        singleLine = true,
//                        modifier = Modifier.fillMaxWidth()
//                    )
//                    Spacer(modifier = Modifier.height(16.dp))
//                    Button(onClick = {
//                        scope.launch {
//                            snackbarHostState.showSnackbar("My name is $textFieldState")
//                        }
//                    }) {
//                        Text("Show my name")
//                    }
//
//
//                }
//            }
//


//            ConstraintLayout(
//                modifier = Modifier
//                    .fillMaxSize(),
//
//            ) {
//                // Create references for the composables to constrain
//                val (greenBox, blueBox, textBox) = createRefs()
//                createVerticalChain(greenBox, blueBox, chainStyle = ChainStyle.Spread)
//                Box (
//
//                    modifier = Modifier
//                        .background(Color.Green)
//                        .constrainAs(greenBox) {
//                        top.linkTo(parent.top)
//                        start.linkTo(parent.start)
//                        width = Dimension.value(100.dp)
//                        height = Dimension.value(100.dp)
//
//                    }
//                )
//
//                Box (
//
//                    modifier = Modifier
//                        .background(Color.Blue)
//                        .constrainAs(blueBox) {
//                            top.linkTo(parent.top)
//                            start.linkTo(blueBox.end)
//                            width = Dimension.value(100.dp)
//                            height = Dimension.value(100.dp)
//
//                        }
//                )
//
//                Text("This is a constrained text placed at the bottom of the top boxes", color= Color.Red, modifier = Modifier
//                    .padding(top=10.dp)
//                    .constrainAs(textBox){
//                        start.linkTo(parent.start)
//                        top.linkTo(blueBox.bottom)
//                    }
//                )
//            }

            Navigation()

        }
        }
}




// Image composable
//@Composable
//fun ImageCard(
//    painter: Painter,
//    contentDescription: String,
//    title: String,
//    modifier: Modifier = Modifier
//) {
//    Card(
//        modifier = modifier
//            .fillMaxWidth(0.5f)
//            .padding(16.dp),
//        shape = RoundedCornerShape(16.dp),
//        elevation = CardDefaults.cardElevation(
//            defaultElevation = 16.dp
//        )
//    ) {
//        Box(modifier = Modifier.height(300.dp)){
//            Image(
//                painter,
//                contentDescription,
//                contentScale = ContentScale.Crop
//            )
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(
//                        Brush.verticalGradient(
//                            colors = listOf(
//                                Color.Transparent,
//                                Color.Black
//                            ),
//                            startY = 300f
//
//                        )
//                    )
//
//            )
//

//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(12.dp),
//                contentAlignment = Alignment.BottomCenter
//            ) {
//                Text(
//                    text = title,
//                    color = Color.White,
//                    fontSize = 15.sp
//                )
//
//            }
//        }
//
//    }
//}

//@Composable
//fun ColorBox(
//    modifier: Modifier = Modifier,
//    updateColor: (Color) -> Unit
//){
//    Box(
//        modifier = modifier.
//        background(
//            Color.Red
//        ).
//        clickable {
//           updateColor(
//               Color(
//                   Random.nextFloat(),
//                   Random.nextFloat(),
//                   Random.nextFloat(),
//                   1f
//               )
//           )
//
//        }
//
//    )
//}
