package com.group.libraryapp.domain.book

import com.group.libraryapp.dto.book.response.BookStatResponse
import com.group.libraryapp.repository.book.BookQuerydslRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface BookRepository: JpaRepository<Book,Long>{
    fun findByName(bookName: String): Book?

    // 특정 DTO로 바로 변환하도록 변환
//    @Query("SELECT NEW com.group.libraryapp.dto.book.response.BookStatResponse(b.type, COUNT(b.id)) FROM Book b GROUP BY b.type")
//    fun getStats():List<BookStatResponse>



}