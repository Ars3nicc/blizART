package com.example.composenavigation.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.composenavigation.ui.models.Notes
import com.example.composenavigation.ui.repository.Resources
import java.text.SimpleDateFormat
import java.util.*

//This features the notes added from Details.kt and displayed in grid format
@OptIn(ExperimentalFoundationApi::class, androidx.compose.animation.ExperimentalAnimationApi::class)
@Composable
fun WorkNotes(
    noteViewModel: HomeViewModel?, //calling the note's view model
    onNoteClick: (id: String) -> Unit,
    navToDetailPage: () -> Unit,
    navToLoginPage: () -> Unit,
    navController: NavHostController
){
    val homeUiState = noteViewModel?.homeUiState ?: HomeUiState()
    var openDialog by remember {
        mutableStateOf(false)
    }
    var selectedNote:Notes? by remember {
        mutableStateOf(null)
    }
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = Unit){
        noteViewModel?.loadNotes()
    }

    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navToDetailPage.invoke() }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = Color.White,
                )
            }
        },
        bottomBar = {BottomBar(navController)},
        topBar = {}
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
                shape = RoundedCornerShape(20.dp),
                elevation = 3.dp,
                backgroundColor = Color(0XFFEB6565)
            )
            {
                Text(
                    text = "My Notes",
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(15.dp),
                    color = Color.White
                )
            }
            when (homeUiState.notesList) {
                is Resources.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(align = Alignment.Center)
                    )
                }
                is Resources.Success -> {
                    Surface(
                        modifier = Modifier.padding(10.dp)
                            .fillMaxSize(),
                        shape = RoundedCornerShape(5.dp)
                    ) {
                        Spacer(modifier = Modifier.padding(10.dp))
                        LazyVerticalGrid(
                            cells = GridCells.Fixed(2),
                            contentPadding = PaddingValues(10.dp),
                        ) {
                            items(
                                homeUiState.notesList.data ?: emptyList()
                            ) { note ->
                                NoteItem(
                                    notes = note,
                                    onLongClick = {
                                        openDialog = true
                                        selectedNote = note
                                    },
                                ) {
                                    onNoteClick.invoke(note.documentId)
                                }
                            }
                        }
                    }
                    //Alert Dialog to Confirm Delete
                    AnimatedVisibility(visible = openDialog) {
                        AlertDialog(
                            onDismissRequest = {
                                openDialog = false
                            },
                            shape = RoundedCornerShape(15.dp),
                            title = { Text(text = "Delete Note?") },
                            confirmButton = {
                                Button(
                                    onClick = {
                                        selectedNote?.documentId?.let {
                                            noteViewModel?.deleteNote(it)
                                        }
                                        openDialog = false
                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = Color(0XFFEB6565)
                                    ),
                                ) {
                                    Text(text = "Confirm")
                                }
                            },
                            dismissButton = {
                                Button(
                                    onClick = { openDialog = false },
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = Color(0xffA0D995)
                                    )
                                ) {
                                    Text(text = "Cancel")
                                }
                            }
                        )


                    }
                }
                else -> {
                    Text(
                        text = homeUiState
                            .notesList.throwable?.localizedMessage ?: "Unknown Error",
                        color = Color.Red
                    )
                }
            }
        }

    }
    LaunchedEffect(key1 = noteViewModel?.hasUser){
        if (noteViewModel?.hasUser == false){
            navToLoginPage.invoke()
        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteItem(
    notes: Notes,
    onLongClick: () -> Unit,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .combinedClickable(
                onLongClick = { onLongClick.invoke() },
                onClick = { onClick.invoke() }
            )
            .padding(5.dp)
            .fillMaxWidth(),
        elevation = 4.dp
    ) {

        Column {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0XFFEB6565)),
                text = notes.title,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Clip,
                textAlign = TextAlign.Center,
                color = Color.White
            )
            Spacer(modifier = Modifier.size(5.dp))
            CompositionLocalProvider(
                LocalContentAlpha provides ContentAlpha.disabled
            ) {
                Text(
                    text = notes.description,
                    style = MaterialTheme.typography.body1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(4.dp),
                    maxLines = 4
                )

            }
            Spacer(modifier = Modifier.size(4.dp))
            CompositionLocalProvider(
                LocalContentAlpha provides ContentAlpha.disabled
            ) {
                Text(
                    text = "",
                    style = MaterialTheme.typography.body1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(4.dp)
                        .align(Alignment.End),
                    maxLines = 4
                )

            }


        }


    }


}




@Preview
@Composable
fun PrevHomeScreen() {
    WorkNotes(
        noteViewModel = null,
        navToDetailPage = {},
        onNoteClick = {  },
        navController = rememberNavController(),
        navToLoginPage = {},
    )
}