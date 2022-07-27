package com.example.composenavigation.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.composenavigation.R
import com.example.composenavigation.ui.Screen
import com.example.composenavigation.ui.navigations.HomeRoutes

data class Cardinfo(val title:String, val type:String ,val detail:String)
data class CategoryInfo(val name:String, val image: Int)
@Composable //Primary Function for all sub functions
fun MainDashboard(
    noteViewModel: HomeViewModel?,
    navController: NavHostController, //Called-out function for navigation
    onSignOut: () -> Unit //Sign out function
){
    val itemlist= mutableListOf<Cardinfo>() //List for Pending Works
    val categlist= mutableListOf<CategoryInfo>() //List for Categories
    itemlist.add(Cardinfo("Requiem","Digital Art" ,"Acrylic/Pastel"))
    itemlist.add(Cardinfo("The Forest Recoloring","Digital Art" ,"Line Art"))
    itemlist.add(Cardinfo("Summer Art","Traditional" ,"Coffee Paint"))
    categlist.add(CategoryInfo("Elf Art", R.drawable.elf))
    categlist.add(CategoryInfo("Fan Art", R.drawable.ghibli))


    Scaffold(bottomBar = {BottomBar(navController)}
    ) {
        Column {
            Spacer(modifier = Modifier.padding(5.dp))
            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
                shape = RoundedCornerShape(20.dp),
                elevation = 3.dp,
            )
            {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp))
                {
                    Row (verticalAlignment = Alignment.CenterVertically){
                        Text(
                            modifier = Modifier
                                .weight(7f),
                            text = "Dashboard",
                            fontWeight = FontWeight.Bold,
                            fontSize = MaterialTheme.typography.h4.fontSize,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = Color(0XFFEB6565)
                        )
                        IconButton(
                            modifier = Modifier
                                .alpha(ContentAlpha.medium)
                                .weight(1f),

                            onClick = {
                                onSignOut.invoke()
                            })
                        {
                            Icon(imageVector =  Icons.Default.ExitToApp,
                                contentDescription = null )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.padding(10.dp))
            Box(contentAlignment = Alignment.Center,
                modifier = Modifier.padding(6.dp)){
                Column() {
                    Text(
                        modifier = Modifier.padding(7.dp)
                            .clickable { navController.navigate(HomeRoutes.WorkNotes.name) },
                        text = "Pending Notes",
                        style = MaterialTheme.typography.h4,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xff68A7AD),
                    )
                    Surface(
                    ) {
                        PendingWorks(data = itemlist)
                    }
                }
            }
            Divider()

            Card(modifier = Modifier
                .fillMaxHeight()
                .padding(10.dp),
                shape = RoundedCornerShape(18.dp)
            ) {
                Column() {
                    Text(
                        modifier = Modifier.padding(7.dp),
                        text = "Target Categories",
                        style = MaterialTheme.typography.h4,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xff68A7AD)
                    )
                    CategoryTab(categlist)
                }


            }


        }
    }
}
@Composable
fun CategoryTab(data: List<CategoryInfo> ){
    LazyColumn {
        items(data) { item ->
            Card(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(40.dp),
                elevation = 4.dp
            ) {
                Row(
                    modifier = Modifier
                        .padding(5.dp)
                ) {
                    Image(painter = painterResource(id = item.image),
                        contentDescription = "profile image",
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop)

                    Column(
                        modifier = Modifier
                            .padding(10.dp)
                            .align(alignment = Alignment.CenterVertically)
                    ) {

                        Text(
                            text = item.name,
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp,
                            color = Color(0XFFEB6565)
                        )
                    }
                }
            }
        }
    }
    
}


@Composable
fun PendingWorks(data: List<Cardinfo>) {
    LazyRow {
        items(data) { item ->
            Card(
                modifier = Modifier
                    .padding(10.dp),
                shape = RoundedCornerShape(7.dp),
                elevation = 4.dp,
                backgroundColor = Color(0XFFEB6565)
            ) {
                Row(
                    modifier = Modifier
                        .padding(10.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(10.dp)
                            .align(alignment = Alignment.CenterVertically)
                    ) {

                        Text(
                            text = item.title,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Color.White
                        )
                        Text(
                            text = item.type,
                            style = MaterialTheme.typography.body2,
                            fontSize = 16.sp,
                            color = Color.White
                        )
                        Text(
                            text = item.detail,
                            style = MaterialTheme.typography.body2,
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val selectedIndex = remember { mutableStateOf(0) }
    BottomNavigation(elevation = 10.dp,
        backgroundColor = Color(0XFFEB6565)) {

        BottomNavigationItem(icon =
        {
            Icon(imageVector = Icons.Default.List,"",
                tint = Color.White)
        },
            label = { Text(text = "To-Do",
                color = Color.White) },
            selected = (selectedIndex.value == 0),
            onClick = {
                navController.navigate(route = Screen.ProgressSelectionScreen.route)
            },
        )

        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.Home,"",
                tint = Color.White)
        },
            label = { Text(text = "Home",
            color = Color.White)},
            selected = (selectedIndex.value == 1),
            onClick = {
                navController.navigate(HomeRoutes.MainDashboard.name)

            })

    }
}




@Composable
fun CommonDialog(
    title: String?,
    state: MutableState<Boolean>,
    content: @Composable (() -> Unit)? = null
) {
    AlertDialog(
        onDismissRequest = {
            state.value = false
        },
        title = title?.let {
            {
                Column(
                    Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(text = title)
                    Divider(modifier = Modifier.padding(bottom = 8.dp))
                }
            }
        },
        text = content,
        dismissButton = {
            Button(onClick = { state.value = false }) {
                Text("Cancel")
            }
        },
        confirmButton = {
            Button(onClick = { }) {
                Text("Ok")
            }
        }, modifier = Modifier.padding(vertical = 8.dp)
    )
}



@Composable
@Preview(showBackground = true)
fun MainDashboardPreview(){
    Surface(modifier = Modifier.fillMaxSize()) {

        MainDashboard(navController = rememberNavController(),
            noteViewModel = null
        ) { }
    }

}
