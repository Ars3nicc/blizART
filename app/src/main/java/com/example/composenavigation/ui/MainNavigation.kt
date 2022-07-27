package com.example.composenavigation.ui

import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.composenavigation.ui.*
import com.example.composenavigation.ui.activity.WorkTrack
import com.example.composenavigation.ui.home.ProgressSelection


@OptIn(ExperimentalMaterialApi::class)
fun NavGraphBuilder.authNavGraph(
    navController: NavHostController
){
    navigation(
        startDestination = Screen.LoginScreen.route,
        route = HOME_ROUTE
    ){
        composable(
            route = Screen.LoginScreen.route
        ){
            Login(navController = navController)
        }
        composable(
            route = Screen.DashboardScreen.route
        ){
            Dashboard(navController = navController)
        }
        composable(
            route = Screen.SignUpScreen.route
        ){
            SignUp(navController = navController)
        }
        composable(
            route = Screen.ProfileScreen.route
        ){
            Profile(navController = navController)
        }
        composable(
            route = Screen.ChecklistScreen.route
        ){
            Checklist(navController = navController)
        }
        composable(
            route = Screen.ProgressSelectionScreen.route
        ){
            ProgressSelection(navController = navController)
        }
        composable(
            route = Screen.WorkTrackScreen.route
        ){
            WorkTrack(navController = navController)
        }
    }

}
