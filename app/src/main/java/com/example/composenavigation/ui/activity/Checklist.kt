package com.example.composenavigation.ui

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composenavigation.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextOverflow
import com.example.composenavigation.ui.home.BottomBar
import com.example.composenavigation.ui.home.PendingWorks


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Checklist(navController: NavHostController){
    Scaffold(bottomBar = {BottomBar(navController)}) {
        Surface(
            modifier = Modifier.fillMaxSize()
        )
        {

            Column {
                Spacer(modifier = Modifier.padding(5.dp))
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                    shape = RoundedCornerShape(20.dp),
                    elevation = 3.dp,
                )
                {
                    Text(
                        text = "To-do Checklist",
                        style = MaterialTheme.typography.h4,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(10.dp),
                        color = Color(0XFFEB6565)

                    )
                    }
                Spacer(modifier = Modifier.padding(15.dp))
                ListScreen()
                }
            }
        }
    }

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ListScreen(){

    Column(modifier = Modifier.fillMaxWidth())
    {
        var listState by remember { mutableStateOf(listOf(""))}
        ListInput { item ->

            listState= listState + listOf(item)
        }

        ItemIndex(listState)
    }
}

@Composable
fun ListInput(onlistAdd: (String) -> Unit){
    var textState by remember { mutableStateOf("")}
    val focusmanager = LocalFocusManager.current
    Column(modifier = Modifier.padding(20.dp)) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = textState,
            onValueChange = {textState=it}
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0XFFEB6565)),
            onClick = { onlistAdd(textState); focusmanager.clearFocus()})
        {
            Text(text = "Add List",
                color = Color.White,
                fontSize = 20.sp
            )
        }
    }

}

@Composable
fun WorkList(data: List<String>){
    LazyColumn {
        items(data.size){ index ->
            Text(text = data[index])
        }
    }
}




@OptIn(ExperimentalAnimationApi::class)
@ExperimentalMaterialApi
@SuppressLint("UnrememberedMutableState")
@Composable
fun ItemIndex(data: List<String>) {
    val deletedItem = remember { mutableStateListOf<String>()}

    LazyColumn {
       itemsIndexed(
           items= data,
           itemContent = {index, item ->
               AnimatedVisibility(
                   visible = !deletedItem.contains(item),
                   enter = expandVertically(),
                   exit = shrinkVertically(
                       animationSpec = tween(durationMillis = 1000)
                   )
               )
               {
                   Card(
                       modifier = Modifier.padding(20.dp),
                       elevation = 4.dp,
                       shape = RoundedCornerShape(20.dp)
                   ) {
                       Row(horizontalArrangement = Arrangement.SpaceBetween,
                           verticalAlignment = Alignment.CenterVertically) {
                           IconButton(
                               onClick = { deletedItem.add(item) }
                           )
                           {
                               Icon(
                                   imageVector = Icons.Filled.Delete,
                                   contentDescription = "null",
                                   tint = Color(0xffEB4747),
                                   modifier = Modifier.size(30.dp)

                                   )

                           }
                           ListItem(
                               text = {
                                   Text(
                                       text = item,
                                       fontWeight = FontWeight.Bold
                                   )
                               },
                               overlineText = {
                                   Text(
                                       text = "To-do",
                                       fontSize = 15.sp,
                                       fontStyle = FontStyle.Italic
                                   )
                               },
                               icon = {
                                   Icon(
                                       imageVector = Icons.Outlined.CheckCircle,
                                       contentDescription = "Person"
                                   )
                               },
                               modifier = Modifier
                                   .background(MaterialTheme.colors.surface)
                                   .padding(10.dp)
                           )
                       }
                   }
               }
           }
       )
    }

}


@Composable
@Preview(showBackground = true)
fun ProgressPreview(){

    Checklist(
        navController = rememberNavController()
    )
}
