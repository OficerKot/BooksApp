package com.example.booksapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.booksapp.R

@Composable
fun LoadingScreen(modifier: Modifier = Modifier){
    Image(
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading),
        contentScale = ContentScale.FillWidth,
        modifier = modifier)
}

@Preview
@Composable
fun LoadingScreenPreview(){
    LoadingScreen(Modifier.fillMaxWidth())
}
