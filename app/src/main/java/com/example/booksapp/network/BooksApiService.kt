package com.example.booksapp.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BooksApiService {
    @GET("volumes")
    suspend fun getBooksId(
        @Query("q") query : String) : BooksIdResponse

    @GET("volumes/{volume_id}")
    suspend fun getBookData(
        @Path("volume_id") id : String) : BookResponse
}