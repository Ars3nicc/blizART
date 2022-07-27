package com.example.composenavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.composenavigation.ui.details.DetailView
import com.example.composenavigation.ui.home.HomeViewModel

import com.example.composenavigation.ui.navigations.SetupNavGraph
import com.example.composenavigation.ui.login.AuthView


class MainActivity : ComponentActivity() {
    lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val loginViewModel = viewModel(modelClass = AuthView::class.java)
            val noteViewModel = viewModel(modelClass = HomeViewModel::class.java)
            val detailView = viewModel(modelClass = DetailView::class.java)
            navController= rememberNavController()
            SetupNavGraph(
                loginViewModel = loginViewModel,
                noteViewModel = noteViewModel,
                detailView = detailView

            )
        }
    }
}
