package com.example.booksapp.data

import com.example.booksapp.network.BookResponse
import com.example.booksapp.network.ImageLinks
import com.example.booksapp.network.VolumeInfo

val testBookResponse1 : BookResponse = BookResponse(
    id = "1",
    volumeInfo = VolumeInfo(
        title = "test AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA BB",
        authors = listOf("A1","B3","A2"),
        imageLinks = ImageLinks(
            smallThumbnail = "http://books.google.com/books/content?id=zysyEQAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api",
            thumbnail ="http://books.google.com/books/content?id=zysyEQAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"
        )
    )
)
val testBookResponse2 : BookResponse = BookResponse(
    id = "2",
    volumeInfo = VolumeInfo(
        title = "test2",
        authors = listOf("A1", "A2"),
        imageLinks = ImageLinks(
            "",
            "",
            "",
            medium = "http://books.google.com/books/content?id=0tz5YpijuksC&printsec=frontcover&img=1&zoom=3&edge=curl&imgtk=AFLRE70CSIz8yM8DYTex1d_aquPcdiwHl3cwW0B3nqUpegN4A74tmtZs3tjZCRkKFDgx6RKwA7b1KXmYxrp1vJZ5PPeP5eFmrGDXDhwY3pQcG_e27Cx5nSBDFDQcR0AT9_t31pIjZAPz&source=gbs_api",
            "",
            ""
        )
    ),
)
val testBookResponse3 : BookResponse = BookResponse(
    id = "3",
    volumeInfo = VolumeInfo(
        title = "test3",
        authors = listOf("A13332323", "AAAAAAAA2"),
        imageLinks = ImageLinks(
            smallThumbnail = "http://books.google.com/books/content?id=AycJAQAAMAAJ&printsec=frontcover&img=1&zoom=5&source=gbs_api",
            thumbnail = "http://books.google.com/books/content?id=AycJAQAAMAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api",
            medium = "http://books.google.com/books/content?id=0tz5YpijuksC&printsec=frontcover&img=1&zoom=3&edge=curl&imgtk=AFLRE70CSIz8yM8DYTex1d_aquPcdiwHl3cwW0B3nqUpegN4A74tmtZs3tjZCRkKFDgx6RKwA7b1KXmYxrp1vJZ5PPeP5eFmrGDXDhwY3pQcG_e27Cx5nSBDFDQcR0AT9_t31pIjZAPz&source=gbs_api"
))
)
val testBookResponse4 : BookResponse = BookResponse(
    id = "4",
    volumeInfo = VolumeInfo(
        title = "test4",
        authors = listOf("A1"),
        imageLinks = ImageLinks(
            smallThumbnail = "http://books.google.com/books/content?id=g4JDEAAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api",
            thumbnail = "http://books.google.com/books/content?id=g4JDEAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api",
            small = "http://books.google.com/books/content?id=0tz5YpijuksC&printsec=frontcover&img=1&zoom=2&edge=curl&imgtk=AFLRE73pkEYpTZMIPhrfrrY-yI3qPc8Ffq_kckxlfzbQy0aF_Gp7ovgT2L8SHWjh2_38ArwTWmTildhwrPiTIjCb4H6Q__HDE1b17aCethxiiIhMUgPRmbp8YRBIBC4vnE-h4qAaH7rf&source=gbs_api",
            medium = "http://books.google.com/books/content?id=0tz5YpijuksC&printsec=frontcover&img=1&zoom=3&edge=curl&imgtk=AFLRE70CSIz8yM8DYTex1d_aquPcdiwHl3cwW0B3nqUpegN4A74tmtZs3tjZCRkKFDgx6RKwA7b1KXmYxrp1vJZ5PPeP5eFmrGDXDhwY3pQcG_e27Cx5nSBDFDQcR0AT9_t31pIjZAPz&source=gbs_api"))
)

val testBooksList = listOf(
    testBookResponse1,
    testBookResponse2,
    testBookResponse3,
    testBookResponse4
)