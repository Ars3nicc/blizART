package com.example.composenavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.composenavigation.ui.Login

@Composable
fun Shape(){
    Canvas(modifier = Modifier.fillMaxSize()) {

        // Fetching width and height for
        // setting start x and end y
        val canvasWidth = size.width
        val canvasHeight = size.height

        // drawing a line between start(x,y) and end(x,y)
        drawLine(
            start = Offset(x = canvasWidth, y = -200000f),
            end = Offset(x = 260f, y = canvasHeight),
            color = Color(0XFFEB6565),
            strokeWidth = 50F
        )
    }
}

@Composable
@Preview(showBackground = true)
fun Shapepreview(){
    Shape(
    )

}
