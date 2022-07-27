package com.example.composenavigation.ui.navigations


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composenavigation.ui.Checklist
import com.example.composenavigation.ui.Screen
import com.example.composenavigation.ui.details.DetailView
import com.example.composenavigation.ui.details.MainNoteBoard
import com.example.composenavigation.ui.home.HomeViewModel
import com.example.composenavigation.ui.home.MainDashboard
import com.example.composenavigation.ui.home.ProgressSelection
import com.example.composenavigation.ui.home.WorkNotes
import com.example.composenavigation.ui.login.AuthView
import com.example.composenavigation.ui.login.LoginScreen
import com.example.composenavigation.ui.login.SignUpScreen

//Main Navigation Panel
enum class LoginRoutes {
    Signup,
    SignIn
}

enum class HomeRoutes {
    MainDashboard,
    Detail,
    WorkNotes
}


//This function holds all navigation elements and destinations
@Composable
fun SetupNavGraph(
    navController: NavHostController = rememberNavController(),
    loginViewModel: AuthView,
    noteViewModel: HomeViewModel,
    detailView: DetailView
) {
    NavHost(
        navController = navController,
        startDestination = LoginRoutes.SignIn.name
    ) {
        composable(route = LoginRoutes.SignIn.name) {
            LoginScreen(onNavToHomePage = {
                navController.navigate(HomeRoutes.MainDashboard.name) {
                    launchSingleTop = true
                    popUpTo(route = LoginRoutes.SignIn.name) {
                        inclusive = true
                    }
                }
            },
                loginViewModel = loginViewModel

            ) {
                navController.navigate(LoginRoutes.Signup.name) {
                    launchSingleTop = true
                    popUpTo(LoginRoutes.SignIn.name) {
                        inclusive = true
                    }
                }
            }
        }

        composable(route = LoginRoutes.Signup.name) {
            SignUpScreen(onNavToHomePage = {
                navController.navigate(HomeRoutes.MainDashboard.name) {
                    popUpTo(LoginRoutes.Signup.name) {
                        inclusive = true
                    }
                }
            },
                loginViewModel = loginViewModel
            ) {
                navController.navigate(LoginRoutes.SignIn.name)
            }
        }

        composable(route = HomeRoutes.MainDashboard.name){
            MainDashboard(
                noteViewModel = noteViewModel,
                navController = navController,
                onSignOut = {
                    navController.popBackStack()
                    navController.navigate(LoginRoutes.SignIn.name)
                    loginViewModel.logoutUser()
                }
            )
        }
        composable(
            route = Screen.ProgressSelectionScreen.route
        ){
            ProgressSelection(navController = navController)
        }

        composable(route = Screen.ChecklistScreen.route){
            Checklist(navController = navController)
        }

        composable(HomeRoutes.WorkNotes.name){
            WorkNotes(
                noteViewModel = noteViewModel,
                onNoteClick = { noteId ->
                    navController.navigate(
                        HomeRoutes.Detail.name + "?id=$noteId"
                    ){
                        launchSingleTop = true
                    }
                },
                navController = navController,
                navToLoginPage = {},
                navToDetailPage = {
                    navController.navigate(HomeRoutes.Detail.name)
                }
            )
        }

        composable(
            route = HomeRoutes.Detail.name + "?id={id}",
            arguments = listOf(navArgument("id"){
                type = NavType.StringType
                defaultValue = ""
            })
        ){ entry ->

            MainNoteBoard(
                detailView = detailView,
                noteId = entry.arguments?.getString("id") as String,
            ) {
                navController.navigateUp()

            }
        }



    }
}




