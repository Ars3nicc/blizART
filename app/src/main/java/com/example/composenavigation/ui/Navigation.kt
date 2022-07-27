package com.example.composenavigation.ui

const val ROOT_ROUTE = "root"
const val DASHBOARD_ROUTE = "dashboard"
const val HOME_ROUTE= "home"

sealed class Screen (val route: String){
    object DashboardScreen: Screen(route = "dashboard")
    object LoginScreen: Screen(route = "login")
    object SignUpScreen: Screen(route = "signup")
    object ProfileScreen: Screen(route = "profile_history")
    object ChecklistScreen: Screen(route = "checklist")
    object ProgressDashboardScreen: Screen(route = "progress_dashboard")
    object MainDashboardScreen: Screen(route = "main_dashboard")
    object ProgressSelectionScreen: Screen(route = "selection")
    object WorkTrackScreen: Screen(route = "works")
}
