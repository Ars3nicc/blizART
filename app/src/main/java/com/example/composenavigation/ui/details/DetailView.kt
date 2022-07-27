package com.example.composenavigation.ui.details

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.composenavigation.ui.models.Notes
import com.example.composenavigation.ui.repository.StorageRepository
import com.google.firebase.auth.FirebaseUser


class DetailView(
    private val repository: StorageRepository = StorageRepository()
):ViewModel(){
    var detailUiState by mutableStateOf(DetailUiState())
        private set

    private val hasUser:Boolean
        get() = repository.hasUser()

    private val user:FirebaseUser?
        get() = repository.user()

    fun  onTitleChange(title: String){
        detailUiState = detailUiState.copy(title= title)
    }
    fun  onContextChange(note: String){
        detailUiState = detailUiState.copy(note= note)
    }


    fun addNote(){
        if(hasUser){
            repository.addNote(
                userId = user!!.uid,
                title = detailUiState.title,
                description = detailUiState.note
            ){
                detailUiState=detailUiState.copy(noteAddedStatus= it)
            }
        }
    }
    fun getNote(noteId:String){
        repository.getNote(
            noteId=noteId,
            onError = {},
        ){
            detailUiState=detailUiState.copy(selectedNote = it)
        }
    }

    fun updateNote(
        noteId:String
    ){
        repository.updateNote(
            title = detailUiState.title,
            note = detailUiState.note,
            noteId= noteId,
            color = detailUiState.colorIndex
        ){
            detailUiState = detailUiState.copy(updateNoteStatus = it)
        }
    }

    fun resetNoteAdded(){
        detailUiState = detailUiState.copy(noteAddedStatus = false, updateNoteStatus = false)
    }
    fun resetState(){
        detailUiState = DetailUiState()
    }



}
data class DetailUiState(
    val colorIndex: Int = 0,
    val title: String = "",
    val note: String = "",
    val noteAddedStatus: Boolean = false,
    val updateNoteStatus: Boolean = false,
    val selectedNote: Notes? = null,
)