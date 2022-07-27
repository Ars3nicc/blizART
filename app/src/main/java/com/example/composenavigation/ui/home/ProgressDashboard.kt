package com.example.composenavigation.ui.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.composenavigation.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.composenavigation.ui.Screen
import com.example.composenavigation.ui.navigations.HomeRoutes

@Composable
fun ProgressSelection(navController: NavHostController){
    Scaffold(
        bottomBar = {BottomBar(navController)}
    ){
        Column() {
            Text(
                modifier = Modifier.padding(7.dp),
                text = "Progress Option",
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.Bold,
                color = Color(0XFFEB6565)
            )
            Spacer(modifier = Modifier.padding(20.dp))
            Card(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxHeight(),
                elevation = 5.dp,
                shape = RoundedCornerShape(10.dp)

            ) {

                Column(
                    modifier = Modifier.padding(15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TodoList(navController)
                    Divider(Modifier.padding(10.dp))
                    WorkInProgress(navController)
                }
            }  
        }

    }
}



@Composable
fun Header(){
    Column(modifier = Modifier.padding(15.dp)) {
        Text(text = "Select Dashboard")
    }
}


@Composable
fun TodoList(navController: NavHostController){
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(R.drawable.todo),
            contentDescription = "null",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
        )
        Column(modifier = Modifier
            .padding(15.dp)
            .align(alignment = Alignment.CenterVertically))
        {

            Text(
                text = "My Checklist",
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
                color = Color(0xff68A7AD)
            )

            Spacer(modifier = Modifier.padding(5.dp))
            Button(
                modifier = Modifier.width(100.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0XFFEB6565)),
                shape = RoundedCornerShape(10.dp),
                onClick = { navController.navigate(route = Screen.ChecklistScreen.route)}
            )
            {
                Text("Go",
                    fontSize = 15.sp,
                    color = MaterialTheme.colors.background
                )

            }

        }

    }

}
@Composable
fun WorkInProgress(navController: NavHostController){
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(R.drawable.handicon),
            contentDescription = "null",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
        )
        Column(modifier = Modifier
            .padding(15.dp)
            .align(alignment = Alignment.CenterVertically))
        {

            Text(
                text = "Work Notes",
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
                color = Color(0xff68A7AD)
            )
            Spacer(modifier = Modifier.padding(5.dp))
            Button(
                modifier = Modifier.width(100.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0XFFEB6565)),
                shape = RoundedCornerShape(10.dp),
                onClick = {navController.navigate(HomeRoutes.WorkNotes.name)}
            )
            {
                Text("Go",
                    fontSize = 15.sp,
                    color = MaterialTheme.colors.background
                )

            }

        }
    }
}

@Composable
@Preview(showBackground = true)
fun DefaultPreview(){
    Surface(modifier = Modifier.fillMaxSize()) {
        ProgressSelection(navController = rememberNavController())
    }



}
