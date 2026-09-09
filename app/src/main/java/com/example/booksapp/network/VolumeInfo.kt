package com.example.booksapp.network

import kotlinx.serialization.Serializable

@Serializable
data class VolumeInfo(
    val title: String = "No title",
    val publishedDate: String = "???",
    val description: String = "No description",
    val authors : List<String> = emptyList(),
    val imageLinks: ImageLinks = ImageLinks(),
    val previewLink: String = ""
)

