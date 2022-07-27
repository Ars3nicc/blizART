package com.example.composenavigation.ui

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
fun Login(navController: NavHostController) {
    var passwordVisibility by remember { mutableStateOf(false)}
    val icon = if (passwordVisibility)
        painterResource(id = R.drawable.design_ic_visibility)
    else
        painterResource(id = R.drawable.design_ic_visibility_off)


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

                    Text(
                        text = "Welcome!",
                        style = MaterialTheme.typography.h4,
                        fontWeight = FontWeight.Bold,
                        color = Color(0XFFEB6565)
                    )
                    OutlinedTextField(
                        value = email.value,
                        leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = "nameIcon") },
                        label = { Text(text = "Email Address") },
                        onValueChange = { email.value = it },
                        singleLine = true,
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    OutlinedTextField(
                        value = password.value,
                        leadingIcon = { Icon(imageVector = Icons.Default.Home, contentDescription = "homeIcon") },
                        label = { Text(text = "Password") },
                        onValueChange = { password.value = it },
                        singleLine = true,
                        trailingIcon = {
                            IconButton(onClick = {
                                passwordVisibility = !passwordVisibility
                            }) {
                                Icon(
                                    painter = icon,
                                    contentDescription = null
                                )
                            }
                        },
                        visualTransformation = if(passwordVisibility) VisualTransformation.None
                        else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password
                        )

                    )

                    Spacer(modifier = Modifier.height(20.dp))
                    Button(modifier = Modifier.width(250.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0XFFEB6565)),
                        shape = RoundedCornerShape(15.dp),
                        onClick = { navController.navigate(route = Screen.MainDashboardScreen.route)}
                    )
                    {
                        Text("Let's Go!",
                            style = MaterialTheme.typography.h6,
                            color = MaterialTheme.colors.background
                        )

                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Text("Sign Up!",
                        style = MaterialTheme.typography.h6,
                        color = Color(0XFF06283D),
                        modifier = Modifier.clickable {
                            navController.navigate(route = Screen.SignUpScreen.route)
                        }
                    )
                }


            }

        }

    }

}
@Composable
@Preview(showBackground = true)
fun LoginPreview(){
    Login(
        navController = rememberNavController()
    )

}
