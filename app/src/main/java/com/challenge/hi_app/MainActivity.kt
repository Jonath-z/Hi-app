package com.challenge.hi_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.challenge.hi_app.ui.theme.HiappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                MainActivityUI {
                    ScreenContent()
                }
        }
    }
}

@Composable
fun MainActivityUI(content: @Composable () -> Unit){
    HiappTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            content()
        }
    }
}

@Composable
fun Greeting(name: String) {
    var isSelected by remember {
        mutableStateOf(false)
    }
    val targetColor by animateColorAsState(
        targetValue = if(isSelected) MaterialTheme.colors.primary else Color.Transparent,
        animationSpec = tween(500)
    )

    Surface(color = targetColor) {
        Text(text = "Hello $name!",
            modifier = Modifier
                .clickable { isSelected = !isSelected }
                .padding(12.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
fun CounterButton(count: Int, updateCount: (Int) -> Unit){
    Button(onClick = { updateCount(count + 1) },
        modifier = Modifier
            .background(Color.Black)
            .fillMaxWidth()
            .height(40.dp)
    )
    {
        Text("I've been clicked $count times")
    }
}

@Composable
fun NamesList(names: List<String>, modifier: Modifier){
     LazyColumn(modifier = modifier) {
         items(names){
             for (name in names){
                 Greeting(name = it)
                 Divider()
             }
         }
     }
}

@Composable
fun ScreenContent(names: List<String> = List(1000){"Hello android devs $it"}){
    var counterState by remember {
        mutableStateOf(0)
    }

    Column (modifier = Modifier.fillMaxHeight()){
        NamesList(names = names, modifier = Modifier.weight(1f))
        CounterButton(count = counterState) { newCount ->
            counterState = newCount
        }

        if(counterState > 5){
            Text("Count is more than five")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainActivityUI {
        ScreenContent()
    }
}