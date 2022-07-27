package com.example.composenavigation.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
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
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composenavigation.R
import com.example.composenavigation.ui.Screen

@Composable
fun LoginScreen(
    loginViewModel: AuthView? = null,
    onNavToHomePage:() -> Unit,
    onNavToSignUpPage:() -> Unit,
) {
    var passwordVisibility by remember { mutableStateOf(false) }
    val icon = if (passwordVisibility)
        painterResource(id = R.drawable.design_ic_visibility)
    else
        painterResource(id = R.drawable.design_ic_visibility_off)


    val loginUiState = loginViewModel?.loginUiState
    val isError = loginUiState?.loginError != null
    val context = LocalContext.current
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

                    if (isError){
                        Text(
                            text = loginUiState?.loginError ?: "unknown error",
                            color = Color.Red,
                        )
                    }

                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        value = loginUiState?.userName ?: "",
                        onValueChange = {loginViewModel?.onUserNameChange(it)},
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = null,
                            )
                        },
                        label = {
                            Text(text = "Email")
                        },
                        isError = isError
                    )
                    Spacer(modifier = Modifier.padding(16.dp))
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        value = loginUiState?.password ?: "",
                        onValueChange = { loginViewModel?.onPasswordNameChange(it) },
                        maxLines = 1,
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = null,
                            )
                        },
                        label = {
                            Text(text = "Password")
                        },
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
                        ),
                        isError = isError
                    )
                    Spacer(modifier = Modifier.padding(7.dp))
                    Button(
                        modifier = Modifier.width(150.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0XFFEB6565)),
                        onClick = { loginViewModel?.loginUser(context) })
                    {
                        Text(
                            text = "Let's Go!",
                            color = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.size(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Text(text = "Don't have an Account?")
                        Spacer(modifier = Modifier.size(8.dp))
                        TextButton(onClick = { onNavToSignUpPage.invoke() }) {
                            Text(text = "SignUp")
                        }

                    }

                    if (loginUiState?.isLoading == true){
                        CircularProgressIndicator()
                    }

                    LaunchedEffect(key1 = loginViewModel?.hasUser){
                        if (loginViewModel?.hasUser == true){
                            onNavToHomePage.invoke()
                        }
                    }

                }


            }

        }

    }



}

@Composable
fun SignUpScreen(
    loginViewModel: AuthView? = null,
    onNavToHomePage:() -> Unit,
    onNavToLoginPage:() -> Unit,
) {
    val loginUiState = loginViewModel?.loginUiState
    val isError = loginUiState?.signUpError != null
    val context = LocalContext.current

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
                        text = "Sign Up",
                        style = MaterialTheme.typography.h3,
                        fontWeight = FontWeight.Black,
                        color = Color(0XFFEB6565)
                    )

                    if (isError){
                        Text(
                            text = loginUiState?.signUpError ?: "unknown error",
                            color = Color.Red,
                        )
                    }

                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        value = loginUiState?.userNameSignUp ?: "",
                        onValueChange = {loginViewModel?.onUserNameChangeSignup(it)},
                        maxLines = 1,
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = null,
                            )
                        },
                        label = {
                            Text(text = "Email")
                        },
                        isError = isError
                    )
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        value = loginUiState?.passwordSignUp ?: "",
                        onValueChange = { loginViewModel?.onPasswordChangeSignup(it) },
                        maxLines = 1,
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = null,
                            )
                        },
                        label = {
                            Text(text = "Password")
                        },
                        isError = isError
                    )
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        value = loginUiState?.confirmPasswordSignUp ?: "",
                        onValueChange = { loginViewModel?.onConfirmPasswordChange(it) },
                        maxLines = 1,
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = null,
                            )
                        },
                        label = {
                            Text(text = "Confirm Password")
                        },
                        isError = isError
                    )

                    Button(
                        modifier = Modifier.width(100.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0XFFEB6565)),
                        onClick = { loginViewModel?.createUser(context) })
                    {
                        Text(text = "Sign In",
                            color = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.size(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Text(text = "Already have an Account?")
                        TextButton(onClick = { onNavToLoginPage.invoke() }) {
                            Text(text = "Sign In")
                        }

                    }

                    if (loginUiState?.isLoading == true){
                        CircularProgressIndicator()
                    }

                    LaunchedEffect(key1 = loginViewModel?.hasUser){
                        if (loginViewModel?.hasUser == true){
                            onNavToHomePage.invoke()
                        }
                    }

                }
            }

        }

    }

}

@Preview(showSystemUi = true)
@Composable
fun PrevLoginScreen() {

    LoginScreen(onNavToHomePage = { /*TODO*/ }) {

    }
}





























