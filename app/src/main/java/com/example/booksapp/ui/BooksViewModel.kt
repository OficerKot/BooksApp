package com.example.booksapp.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.booksapp.BooksApplication
import com.example.booksapp.data.BooksRepository
import com.example.booksapp.network.BookResponse
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface UiState{
    data class Success(val books: List<BookResponse>) : UiState
    data class Error(val message: String = "") : UiState
    object Loading : UiState
    object Empty : UiState

}

class BooksViewModel(val booksRepository: BooksRepository) : ViewModel() {
    var uiState : UiState by mutableStateOf(UiState.Empty)
        private set
    private var query : String by mutableStateOf(value = "")

    fun updateQuery(newQuery : String){
        query = newQuery
        if(query == ""){
            uiState = UiState.Empty
        }
        else {
            uiState = UiState.Loading
            getBooks()
        }
    }
    fun getBooks(){
        viewModelScope.launch {
            uiState = try{
                UiState.Success(booksRepository.getBooksResponses(query))
            }
            catch (e: retrofit2.HttpException) {
                Log.e(
                    "BOOKS_API",
                    "CODE=${e.code()} ERROR=${e.response()?.errorBody()?.string()}"
                )
                UiState.Error("Ошибка HTTP: " + e.message + e)
            }
            catch (e : IOException){
                UiState.Error("Ошибка соединения")
            }
        }
    }

    companion object{
        val Factory : ViewModelProvider.Factory = viewModelFactory{
            initializer {
                val app = this[APPLICATION_KEY] as BooksApplication
                BooksViewModel(app.container.repository)
            }
        }
    }
}