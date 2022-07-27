package com.example.composenavigation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.composenavigation.R
import com.example.composenavigation.ui.home.BottomBar

@Composable
fun Dashboard(navController: NavHostController) {
    val description = "card"
    val myprojects = "My Projects"
    val myprofile = "Profile"
    val checklist= "Progress"

    Scaffold(bottomBar = {BottomBar(navController)}) {
        Surface(modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 30.dp)) {
            Box()
            {

                Column(
                    Modifier
                        .verticalScroll(rememberScrollState())
                        .fillMaxSize()
                ) {
                    Column(modifier = Modifier
                        .align(Alignment.Start)
                        .padding(horizontal = 10.dp)
                    ) {
                        Text(
                            text = "Hi!",
                            style = MaterialTheme.typography.h3,
                            fontWeight = FontWeight.Black,
                            color = Color(0XFF06283D)
                        )
                        Text(
                            text = "Where do you want to go?",
                            fontStyle = FontStyle.Italic,
                            fontSize = 25.sp,
                            color = Color(0XFF06283D)
                        )
                    }

                    Column(
                        Modifier.padding(12.dp),
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally)
                    {

                        ProgressCard(
                            navController,
                            painter = painterResource(id = R.drawable.myprogress),
                            contentDescription = description,
                            title = checklist,
                            Modifier
                                .alpha(0.9f)
                                .fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.padding(12.dp))
                        ProfileCard(
                            navController,
                            painter = painterResource(id = R.drawable.me),
                            contentDescription = description,
                            title = myprofile,
                            Modifier.alpha(0.9f)
                        )

                        Spacer(modifier = Modifier.padding(30.dp))
                        Button(
                            modifier = Modifier.width(120.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFEB4545)),
                            shape = RoundedCornerShape(15.dp),
                            onClick = { navController.navigate(route = Screen.DashboardScreen.route)}
                        )
                        {
                            Text("Sign Out",
                                style = MaterialTheme.typography.h6,
                                color = MaterialTheme.colors.background
                            )

                        }
                    }

                }

            }
        }
    }
}
@Composable
fun ProgressCard(
    navController: NavHostController,
    painter: Painter,
    contentDescription: String,
    title: String,
    modifier: Modifier = Modifier)
{
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { navController.navigate(route = Screen.ProgressSelectionScreen.route) },
        shape = RoundedCornerShape(15.dp),
        elevation = 14.dp
    ) {
        Box(modifier = modifier.height(100.dp))
        {
            Image(
                painter = painter,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop,
            )
            Box(modifier = modifier
                .fillMaxSize()
                .padding(12.dp),
                contentAlignment = Alignment.Center
            )
            {
                Text(title,
                    color = Color.White,
                    style = MaterialTheme.typography.h3,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center

                )
            }
        }

    }

}

@Composable
fun Projects(
    navController: NavHostController,
    painter: Painter,
    contentDescription: String,
    title: String,
    modifier: Modifier = Modifier)
{
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { navController.navigate(route = Screen.DashboardScreen.route) },
        shape = RoundedCornerShape(15.dp),
        elevation = 14.dp
    ) {
        Box(modifier = modifier.height(100.dp))
        {
            Image(
                painter = painter,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop
            )
            Box(modifier = modifier
                .fillMaxSize()
                .padding(12.dp),
                contentAlignment = Alignment.Center
            )
            {
                Text(title,
                    color = Color.White,
                    style = MaterialTheme.typography.h3,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center

                )
            }
        }

    }

}

@Composable
fun ProfileCard(
    navController: NavHostController,
    painter: Painter,
    contentDescription: String,
    title: String,
    modifier: Modifier = Modifier)
{
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { navController.navigate(route = Screen.ProfileScreen.route) },
        shape = RoundedCornerShape(15.dp),
        elevation = 14.dp
    ) {
        Box(modifier = modifier.height(100.dp))
        {
            Image(
                painter = painter,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop
            )
            Box(modifier = modifier
                .fillMaxSize()
                .padding(12.dp),
                contentAlignment = Alignment.Center
            )
            {
                Text(title,
                    color = Color.White,
                    style = MaterialTheme.typography.h3,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center

                )
            }
        }

    }

}

@Composable
@Preview(showBackground = true)
fun DashboardPreview(){
    Dashboard(
        navController = rememberNavController()
    )
}
