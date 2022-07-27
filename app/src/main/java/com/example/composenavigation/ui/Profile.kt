package com.example.composenavigation.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.composenavigation.R
import com.example.composenavigation.ui.home.BottomBar

@Composable
fun Profile(navController: NavHostController){
    Scaffold(bottomBar = {BottomBar(navController)}) {
        Column(

            Modifier
                .fillMaxSize()
                .padding(vertical = 30.dp)
        )
        {
            Row(Modifier.padding(10.dp)) {
                Image(
                    painter = painterResource(R.drawable.me),
                    contentDescription = "null",
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape)
                )
                Column(
                    modifier = Modifier
                        .align(alignment = Alignment.CenterVertically)
                        .padding(12.dp)
                )
                {
                    Text(text = "Franz Manrique",
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp,
                        color = Color(0XFF06283D)
                    )

                }
            }
            Column(verticalArrangement = Arrangement.SpaceEvenly)
            {

                MyEducationExpanded()
                Achievements()
            }

        }
    }


}



@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyEducationExpanded(){
    var expandedState by remember { mutableStateOf(false)}
    val rotationState by animateFloatAsState(
        targetValue = if(expandedState) 180f else 0f
    )

    Card(modifier = Modifier
        .fillMaxWidth()
        .animateContentSize(
            animationSpec = tween(
                durationMillis = 300,
                easing = LinearOutSlowInEasing
            )
        ),
        shape = RoundedCornerShape(20.dp),
        onClick = {
            expandedState = !expandedState
        }
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)) {
            Row (verticalAlignment = Alignment.CenterVertically){
                Text(
                    modifier = Modifier
                        .weight(6f),
                    text = "My Education",
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.h6.fontSize,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis

                )
                IconButton(
                    modifier = Modifier
                        .alpha(ContentAlpha.medium)
                        .weight(1f)
                        .rotate(rotationState),
                    onClick = {
                        expandedState = !expandedState
                    }) {
                    Icon(imageVector =  Icons.Default.ArrowDropDown,
                        contentDescription = null )

                }

            }
            if (expandedState){
                Text(text = "MARINDUQUE NATIONAL HIGH SCHOOL")
            }

        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Achievements(){
    var expandedState by remember { mutableStateOf(false)}
    val rotationState by animateFloatAsState(
        targetValue = if(expandedState) 180f else 0f
    )

    Card(modifier = Modifier
        .fillMaxWidth()
        .animateContentSize(
            animationSpec = tween(
                durationMillis = 300,
                easing = LinearOutSlowInEasing
            )
        ),
        shape = RoundedCornerShape(20.dp),
        onClick = {
            expandedState = !expandedState
        })
    {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)) {
            Row (verticalAlignment = Alignment.CenterVertically){
                Text(
                    modifier = Modifier
                        .weight(6f),
                    text = "Achievements",
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.h6.fontSize,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis

                )
                IconButton(
                    modifier = Modifier
                        .alpha(ContentAlpha.medium)
                        .weight(1f)
                        .rotate(rotationState),
                    onClick = {
                        expandedState = !expandedState
                    }) {
                    Icon(imageVector =  Icons.Default.ArrowDropDown,
                        contentDescription = null )

                }

            }
            if (expandedState){
                Text(text = "MARINDUQUE NATIONAL HIGH SCHOOL")
            }

        }
    }
}

@Composable
@Preview(showBackground = true)
fun MyEducationPreview(){

    Profile(navController = rememberNavController())


}
