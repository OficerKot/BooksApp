package com.example.booksapp.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.LibraryBooks
import androidx.compose.material.icons.filled.LibraryBooks
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.booksapp.R
import com.example.booksapp.ui.screens.HomeScreen
import com.example.compose.BooksAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BooksApp() {
    val booksVM : BooksViewModel = viewModel(factory = BooksViewModel.Factory)
   BooksAppTheme() {
       Scaffold(topBar = {
           TopAppBar(title = {
               Row(verticalAlignment = Alignment.CenterVertically) {
                   Text(
                       text = stringResource(R.string.app_name),
                       style = MaterialTheme.typography.headlineMedium,
                       modifier = Modifier.padding(end = 8.dp)
                   )

                   Icon(
                       imageVector = Icons.AutoMirrored.Filled.LibraryBooks,
                       contentDescription = null
                   )
               }
           })
       }) { innerPadding ->
           HomeScreen(
               uiState = booksVM.uiState,
               retryAction = booksVM::getBooks,
               onSearch = booksVM::updateQuery,
               modifier = Modifier
                   .padding(innerPadding)
           )
       }
   }
}

@Composable
@Preview
fun BooksAppPreview(){
    BooksApp()
}

