package com.example.composenavigation.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composenavigation.ui.models.Notes
import com.example.composenavigation.ui.repository.Resources
import com.example.composenavigation.ui.repository.StorageRepository
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class HomeViewModel(
    private val repository: StorageRepository = StorageRepository()
):ViewModel()
{
    var homeUiState by mutableStateOf(HomeUiState())

    val user= repository.user()
    val hasUser:Boolean
        get() = repository.hasUser()
    private val userId:String
        get()= repository.getUserId()

    fun loadNotes(){
        if(hasUser){
            if (userId.isNotBlank()){
                getUserNotes(userId)
            }
        }
        else{
            homeUiState = homeUiState.copy(notesList = Resources.Error(
                throwable = Throwable(message = "User not Logged In")
            ))
        }
    }
    private fun getUserNotes(userId:String) = viewModelScope.launch {
        repository.getUserNotes(userId).collect(){
            homeUiState=homeUiState.copy(notesList = it)
        }
    }

    fun deleteNote(noteId:String) = repository.deleteNote(noteId){
        homeUiState=homeUiState.copy(noteDeletedStatus = it)
    }

}

data class HomeUiState(
    val notesList: Resources<List<Notes>> = Resources.Loading(),
    val noteDeletedStatus: Boolean = false,
)

