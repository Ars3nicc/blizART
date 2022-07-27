package com.example.composenavigation.ui

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.composenavigation.R

@Composable
fun SignUp(navController: NavHostController) {
    val showAlert = remember { mutableStateOf(false)}
    val email = remember { mutableStateOf(TextFieldValue()) }
    val password= remember { mutableStateOf(TextFieldValue()) }
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.background(Color(0XFFEB6565)))
        {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "Contact profile picture",
                modifier = Modifier
                    .size(200.dp)
            )
            Card(modifier = Modifier
                .padding(5.dp),
                shape = RoundedCornerShape(25.dp)
            )
            {
                Column(modifier = Modifier
                    .padding(25.dp),
                    horizontalAlignment = Alignment.CenterHorizontally) {

                    Text(text = "Register",
                        fontWeight = FontWeight.Bold,
                        color = Color(0XFFEB6565),
                        style = MaterialTheme.typography.h5

                    )

                    OutlinedTextField(
                        value = email.value,
                        label = { Text(text = "Enter Email Address") },
                        onValueChange = { email.value = it },
                        singleLine = true,
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    OutlinedTextField(
                        value = password.value,
                        label = { Text(text = "Enter Password") },
                        onValueChange = { password.value = it },
                        singleLine = true

                    )

                    Spacer(modifier = Modifier.height(20.dp))
                    Button(modifier = Modifier.width(150.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0XFFEB6565)),
                        shape = RoundedCornerShape(15.dp),
                        onClick = { navController.navigate(route = Screen.LoginScreen.route)}
                    )
                    {
                        Text("Sign Up",
                            style = MaterialTheme.typography.h6,
                            color = MaterialTheme.colors.background
                        )

                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Text("Change your mind?",
                        style = MaterialTheme.typography.h6,
                        color = Color(0XFF06283D),
                        modifier = Modifier.clickable {
                            navController.navigate(route = Screen.LoginScreen.route)
                        }
                    )
                }


            }

        }

    }

}


@Composable
@Preview(showBackground = true)
fun SignUpPreview(){
    SignUp(
        navController = rememberNavController()
    )

}
