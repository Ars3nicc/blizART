package com.example.composenavigation.ui.activity

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.composenavigation.ui.home.BottomBar

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun WorkTrack(navController: NavHostController){
    Scaffold(bottomBar = {BottomBar(navController)}) {
        Column() {
            WorkHeader()
            AddTrackButtonScreen()
        }


    }

}
@Composable
fun WorkHeader(){
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = 3.dp,
    )
    {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)) {
            Row (verticalAlignment = Alignment.CenterVertically){
                Text(
                    modifier = Modifier
                        .weight(7f),
                    text = "Work In Progress",
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.h4.fontSize,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Color(0XFFEB6565)

                )
            }

        }
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddTrackButtonScreen(){
    val inputDialogState = remember {
        mutableStateOf(false)
    }
    Button(
        onClick = {
        inputDialogState.value = true
    }) {
        Text("New Track")
    }
    if (inputDialogState.value) {
        CommonDialog(title = null, state = inputDialogState) {
            InputView()
        }
    }
}


@ExperimentalComposeUiApi
@Composable
fun InputView() {
    Column(
        Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Create New Art Track", fontSize = 16.sp)
        Divider()
        ArtConcept()
    }
}

@ExperimentalComposeUiApi
@Composable
fun ArtConcept(
    modifier: Modifier = Modifier,
    title: MutableState<String> = remember {
        mutableStateOf("")
    }, detail: MutableState<String> = remember {
        mutableStateOf("")
    }
) {
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val isShowPassword = remember { mutableStateOf(false) }

    Column(
        modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(
            modifier= Modifier.fillMaxWidth(),
            value = title.value,
            onValueChange = { title.value = it },
            label = { Text("Username") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = {
                keyboardController?.hide()
                focusRequester.requestFocus()
            }),
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester),
            value = detail.value,
            onValueChange = { detail.value = it },
            label = { Text("Password") },
            trailingIcon = {
                IconButton(onClick = { isShowPassword.value = !isShowPassword.value }) {
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                keyboardController?.hide()
            })
        )
    }
}

@Composable
fun AlertDialogView(state: MutableState<Boolean>) {
    CommonDialog(title = "Alert Dialog", state = state) {
        Text("JetPack Compose Alert Dialog!")
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
            Button(onClick = { state.value = false }) {
                Text("Add Track")
            }
        }, modifier = Modifier.padding(vertical = 8.dp)
    )
}



@Composable
@Preview(showBackground = true)
fun WorkTrackView(){

    WorkTrack(
        navController = rememberNavController()
    )
}
