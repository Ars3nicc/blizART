package com.example.composenavigation.ui.details

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composenavigation.ui.ListScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainNoteBoard(
    detailView: DetailView?,
    noteId:String,
    onNavigate:() -> Unit
){
    val detailUiState = detailView?.detailUiState?: DetailUiState()

    val isFormNotBlank = detailUiState.note.isNotBlank() &&
            detailUiState.title.isNotBlank()

    val isNoteIdNotBlank = noteId.isNotBlank()
    val icon = if(isNoteIdNotBlank) Icons.Default.Refresh
        else Icons.Default.Check
    LaunchedEffect(key1 = Unit){
        if (isNoteIdNotBlank){
            detailView?.getNote(noteId)
        }
        else{
            detailView?.resetState()
        }
    }

    val scope= rememberCoroutineScope()
    val scaffoldState= rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {
            AnimatedVisibility(visible = isFormNotBlank) {
                FloatingActionButton(
                    onClick = {
                        if (isNoteIdNotBlank) {
                            detailView?.updateNote(noteId)
                        } else {
                            detailView?.addNote()
                        }
                    }
                ) {
                    Icon(imageVector = icon, contentDescription = null)
                }
            }
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
        ) {
            if (detailUiState.noteAddedStatus) {
                scope.launch {
                    scaffoldState.snackbarHostState
                        .showSnackbar("New Note Added")
                    detailView?.resetNoteAdded()
                    onNavigate.invoke()
                }
            }

            if (detailUiState.updateNoteStatus) {
                scope.launch {
                    scaffoldState.snackbarHostState
                        .showSnackbar("Note Updated")
                    detailView?.resetNoteAdded()
                    onNavigate.invoke()
                }
            }

            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
                shape = RoundedCornerShape(20.dp),
                elevation = 3.dp,
                backgroundColor = Color(0XFFEB6565)
            )
            {
                Text(
                    text = "Noteboard",
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(10.dp),
                    color = Color.White
                )
            }
            Text(
                text = "Create Note",
                modifier = Modifier.padding(10.dp),
                style = MaterialTheme.typography.h5,
                color = Color(0xff2C3333)
            )
            Card(
                modifier = Modifier.padding(10.dp),
                shape = RoundedCornerShape(5.dp)
            ) {
                Column() {
                    OutlinedTextField(
                        value = detailUiState.title,
                        onValueChange = {
                            detailView?.onTitleChange(it)
                        },
                        label = { Text(text = "Note title") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )
                    OutlinedTextField(
                        value = detailUiState.note,
                        onValueChange = { detailView?.onContextChange(it) },
                        label = { Text(text = "Create note here dumdum") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(2f)
                            .padding(8.dp)
                    )

                }

            }

        }
    }
}
@Preview(showBackground = true)
@Composable
fun PrevDetailScreen() {
    MainNoteBoard(detailView = null, noteId = "") {

    }

}