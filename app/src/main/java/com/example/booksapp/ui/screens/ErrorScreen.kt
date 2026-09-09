package com.example.booksapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.booksapp.R

@Composable
fun ErrorScreen(
    retryAction: () -> Unit,
    message: String,
    modifier: Modifier = Modifier){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(R.drawable.ic_connection_error),
            contentDescription = stringResource(R.string.loading)
        )

        Text(
            style = MaterialTheme.typography.displaySmall,
            text = stringResource(R.string.error)
        )

        Text(
            style = MaterialTheme.typography.headlineMedium,
            text = message,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Button(onClick = retryAction) {
            Text(
                text = stringResource(R.string.retry),
                style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Preview
@Composable
fun ErrorScreenPreview(){
    ErrorScreen(
        {},
        message = "Ошибка...",
        Modifier.fillMaxSize())
}