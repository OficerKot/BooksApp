package com.example.booksapp.ui.screens

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.booksapp.R
import com.example.booksapp.data.testBookResponse3
import com.example.booksapp.network.BookResponse
import com.example.booksapp.network.toHttps
import androidx.core.net.toUri
import com.example.compose.BooksAppTheme
import org.jsoup.Jsoup

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookInfoSheet(
    onDismissRequest: () -> Unit,
    bookResponse: BookResponse,
    modifier: Modifier = Modifier
){
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        dragHandle = {},
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        scrimColor = MaterialTheme.colorScheme.scrim.copy(alpha = 0.5f),
        tonalElevation = 0.dp
    ) {
        val context = LocalContext.current

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(toHttps(bookResponse.volumeInfo.imageLinks.large))
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                error = painterResource(R.drawable.ic_broken_image),
                placeholder = painterResource(R.drawable.loading_img),
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth()
            )

            IconButton(
                onClick = onDismissRequest,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = stringResource(R.string.back),
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .weight(0.5f)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                style = MaterialTheme.typography.headlineMedium,
                text = bookResponse.volumeInfo.title,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = MaterialTheme.typography.bodyLarge
                            .copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            .toSpanStyle()
                    ) {
                        append(text = "${stringResource(R.string.authors)}: ")
                    }

                    withStyle(
                        style = MaterialTheme.typography.bodyLarge
                            .copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                            .toSpanStyle()
                    ) {
                        append(text = bookResponse.volumeInfo.authors.joinToString())
                    }
                },
                modifier = Modifier.padding(bottom = 8.dp)
            )


            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = MaterialTheme.typography.bodyLarge
                            .copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            .toSpanStyle()
                    ) {
                        append(text = "${stringResource(R.string.description)}: ")
                    }

                    withStyle(
                        style = MaterialTheme.typography.bodyLarge
                            .copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                            .toSpanStyle()
                    ) {
                        append(text = Jsoup.parse(bookResponse.volumeInfo.description).text())
                    }
                },
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = MaterialTheme.typography.bodyLarge
                            .copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            .toSpanStyle()
                    ) {
                        append(text = "${stringResource(R.string.published_date)}: ")
                    }

                    withStyle(
                        style = MaterialTheme.typography.bodyLarge
                            .copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                            .toSpanStyle()
                    ) {
                        append(text = bookResponse.volumeInfo.publishedDate)
                    }
                },
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Button(
                onClick = {
                    val intent = Intent(ACTION_VIEW, bookResponse.volumeInfo.previewLink.toUri())
                    context.startActivity(intent)
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    disabledContainerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
                    disabledContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 0.dp,
                    pressedElevation = 2.dp
                )
            ) {
                Text(
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight(500),
                    text = stringResource(R.string.open),
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@Composable
@Preview
fun BookInfoSheetPreview() {
    BooksAppTheme() {
        Scaffold() { innerPadding ->
            BookInfoSheet(
                onDismissRequest = {},
                bookResponse = testBookResponse3,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}