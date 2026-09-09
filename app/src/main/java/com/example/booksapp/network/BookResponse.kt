package com.example.booksapp.network

import kotlinx.serialization.Serializable

@Serializable
data class BookResponse(
    val id: String,
    val volumeInfo: VolumeInfo,
)

@Serializable
data class BooksIdResponse(
    val items: List<BookId>
)

@Serializable
data class BookId(
    val id: String
)

fun toHttps(url:String?) : String?{
    return url?.replaceFirst("http://", "https://")
}