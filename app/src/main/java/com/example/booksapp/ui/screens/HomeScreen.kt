package com.example.booksapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.booksapp.R
import com.example.booksapp.data.testBooksList
import com.example.booksapp.network.BookResponse
import com.example.booksapp.network.toHttps
import com.example.booksapp.ui.UiState

@Composable
fun HomeScreen(
    uiState : UiState,
    onSearch: (String) -> Unit,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
){

    var query : String by rememberSaveable{ mutableStateOf("") }
    var openedBookId : String? by rememberSaveable{ mutableStateOf(null) }
    val openedBook = if(uiState is UiState.Success && openedBookId != null) {
        uiState.books.find { book -> book.id == openedBookId }
    } else null

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = modifier) {
        BookSearchBar(
            query = query,
            onQueryChange = {query = it},
            onSearch = onSearch,
            modifier = Modifier
                .fillMaxWidth()
        )

        openedBook?.let {
            BookInfoSheet(
                onDismissRequest = {openedBookId = null},
                bookResponse = it
            )
        }
        when (uiState) {
            UiState.Empty -> NoQueryScreen(Modifier.fillMaxSize())
            UiState.Loading -> LoadingScreen(Modifier.fillMaxSize())
            is UiState.Error -> ErrorScreen(
                retryAction = retryAction,
                message = uiState.message,
                modifier = Modifier.fillMaxSize()
            )

            is UiState.Success ->
                BooksList(
                    onCardClick = {openedBookId = it},
                uiState.books,
                modifier = Modifier.fillMaxSize()
            )

            }
        }
    }


@Composable
fun BooksList(
    onCardClick: (String)->Unit,
    booksList: List<BookResponse>,
    modifier: Modifier = Modifier
){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        modifier = modifier) {
        items(booksList, key = {it.id}){
            book -> BookCard(
            onClick = onCardClick,
            book = book,
            Modifier
                .height(320.dp)
                .padding(4.dp))
        }
    }
}

@Composable
fun BookCard(
    onClick: (String)->Unit,
    book: BookResponse,
    modifier: Modifier = Modifier
){
    Card(
        shape = MaterialTheme.shapes.extraSmall,
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            disabledContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            disabledContainerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier.clickable(
            onClick = {onClick(book.id)}
        )
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(toHttps(book.volumeInfo.imageLinks.medium))
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.FillWidth,
                contentDescription = null,
                error = painterResource(R.drawable.ic_broken_image),
                placeholder = painterResource(R.drawable.loading_img),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.7f)
            )

            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(0.3f)
                    .padding(vertical = 4.dp)) {
                Text(
                    text = book.volumeInfo.title,
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2
                )

                Text(
                    text = book.volumeInfo.authors.joinToString(),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(vertical = 3.dp)
                )
            }
        }
    }
}


@Preview
@Composable
fun HomeScreenPreview(){
    HomeScreen(
        uiState = UiState.Success(testBooksList),
        onSearch = {},
        retryAction = {}
    )
}




