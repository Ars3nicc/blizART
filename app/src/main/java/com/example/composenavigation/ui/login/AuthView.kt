package com.example.composenavigation.ui.login

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composenavigation.ui.repository.AuthRepository
import kotlinx.coroutines.launch

//View Model for authentication phase (Login & Sign Up)
class AuthView(
    private val repository: AuthRepository = AuthRepository(), //Firebase Account repository
) : ViewModel() {
    val currentUser = repository.currentUser

    val hasUser: Boolean
        get() = repository.hasUser() //State variable to check if user is valid or not

    var loginUiState by mutableStateOf(LoginUiState()) //State variable for login validity
        private set

    //Functions to call input fields
    fun onUserNameChange(userName: String) {
        loginUiState = loginUiState.copy(userName = userName)
    }
    fun onPasswordNameChange(password: String) {
        loginUiState = loginUiState.copy(password = password)
    }
    fun onUserNameChangeSignup(userName: String) {
        loginUiState = loginUiState.copy(userNameSignUp = userName)
    }
    fun onPasswordChangeSignup(password: String) {
        loginUiState = loginUiState.copy(passwordSignUp = password)
    }
    fun onConfirmPasswordChange(password: String) {
        loginUiState = loginUiState.copy(confirmPasswordSignUp = password)
    }

    //Validating input fields if it contains elements (strings/int)
    //Account validation for Log In
    private fun validateLoginForm() =
        loginUiState.userName.isNotBlank() &&
                loginUiState.password.isNotBlank()

    //Account validation for Sign Up
    private fun validateSignupForm() =
        loginUiState.userNameSignUp.isNotBlank() &&
                loginUiState.passwordSignUp.isNotBlank() &&
                loginUiState.confirmPasswordSignUp.isNotBlank()


    //This function creates new account and stored to firebase console
    fun createUser(context: Context) = viewModelScope.launch {
        try {
            if (!validateSignupForm()) {
                throw IllegalArgumentException("Fields cannot be empty")
            }
            //Checking if password field matches to the confirmation
            loginUiState = loginUiState.copy(isLoading = true)
            if (loginUiState.passwordSignUp !=
                loginUiState.confirmPasswordSignUp
            ) {
                throw IllegalArgumentException(
                    "Password do not match"
                )
            }

            loginUiState = loginUiState.copy(signUpError = null) //If account if verified, it will store in Firebase
            repository.createUser(
                loginUiState.userNameSignUp,
                loginUiState.passwordSignUp
            ) { isSuccessful ->
                if (isSuccessful) {
                    Toast.makeText(
                        context,
                        "Login Successful",
                        Toast.LENGTH_SHORT
                    ).show()
                    loginUiState = loginUiState.copy(isSuccessLogin = true)
                } else {
                    Toast.makeText(
                        context,
                        "Failed Login",
                        Toast.LENGTH_SHORT
                    ).show()
                    loginUiState = loginUiState.copy(isSuccessLogin = false)
                }

            }


        } catch (e: Exception) {
            loginUiState = loginUiState.copy(signUpError = e.localizedMessage)
            e.printStackTrace()
        } finally {
            loginUiState = loginUiState.copy(isLoading = false)
        }

    }

    //This function will verify the registered account
    fun loginUser(context: Context) = viewModelScope.launch {
        try {
            if (!validateLoginForm()) {
                throw IllegalArgumentException("Fields cannot be empty")
            }
            loginUiState = loginUiState.copy(isLoading = true)
            loginUiState = loginUiState.copy(loginError = null)
            repository.login(
                loginUiState.userName,
                loginUiState.password
            ) { isSuccessful ->
                loginUiState = if (isSuccessful) {
                    Toast.makeText(
                        context,
                        "Welcome",
                        Toast.LENGTH_SHORT
                    ).show()
                    loginUiState.copy(isSuccessLogin = true)
                } else {
                    Toast.makeText(
                        context,
                        "Failed Login",
                        Toast.LENGTH_SHORT
                    ).show()
                    loginUiState.copy(isSuccessLogin = false)
                }
            }


        } catch (e: Exception) {
            loginUiState = loginUiState.copy(loginError = e.localizedMessage)
            e.printStackTrace()
        } finally {
            loginUiState = loginUiState.copy(isLoading = false)
        }
    }

    //This function will only log out the user session once clicked on a designated button/text
    fun logoutUser() = viewModelScope.launch {
        loginUiState = LoginUiState()
        repository.logout()
    }

    //Input elements to use for registration and logging in
    data class LoginUiState(
        val userName: String = "",
        val password: String = "",
        val userNameSignUp: String = "",
        val passwordSignUp: String = "",
        val confirmPasswordSignUp: String = "",
        val isLoading: Boolean = false,
        val isSuccessLogin: Boolean = false,
        val signUpError: String? = null,
        val loginError: String? = null,
    )

}

















