package com.example.booksapp.data
import com.example.booksapp.network.BooksApiService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

interface AppContainer {
    val repository : BooksRepository
}

class DefaultAppContainer() : AppContainer{
    private val BASE_URL = "https://www.googleapis.com/books/v1/"

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val url = chain.request().url.newBuilder()
                .addQueryParameter(
                    "key",
                    com.example.booksapp.BuildConfig.GOOGLE_BOOKS_API_KEY
                )
                .build()

            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()

            chain.proceed(request)
        }
        .build()
    private val json = Json {
        ignoreUnknownKeys = true
    }

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(
            json.asConverterFactory("application/json".toMediaType())
        )
        .build()

    val booksApiService by lazy {retrofit.create(BooksApiService::class.java)}
    override val repository : BooksRepository by lazy { NetworkBooksRepository(booksApiService) }
    //override val repository : BooksRepository by lazy { TestBooksRepository() }
}