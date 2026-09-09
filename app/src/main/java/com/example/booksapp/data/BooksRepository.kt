package com.example.booksapp.data

import com.example.booksapp.network.BookId
import com.example.booksapp.network.BookResponse
import com.example.booksapp.network.BooksApiService
import com.example.booksapp.network.BooksIdResponse

interface BooksRepository{
    suspend fun getBooksId(query: String) : BooksIdResponse
    suspend fun getBooksResponses(query: String) : List<BookResponse>
}

class NetworkBooksRepository(val booksApiService: BooksApiService) : BooksRepository{

    override suspend fun getBooksId(query: String): BooksIdResponse {
        return booksApiService.getBooksId(query)
    }

    override suspend fun getBooksResponses(query: String): List<BookResponse> {
        return getBooksId(query).items.map {booksApiService.getBookData(it.id) }
    }

}

class TestBooksRepository: BooksRepository{

    override suspend fun getBooksId(query: String): BooksIdResponse {
        return BooksIdResponse(listOf(BookId(id = "id2"), BookId(id = "id2"), BookId(id = "id2"), BookId(id = "id2")))
    }
    override suspend fun getBooksResponses(query: String): List<BookResponse> {
        return listOf(
            testBookResponse1,
            testBookResponse2,
            testBookResponse3,
            testBookResponse4)
    }
}