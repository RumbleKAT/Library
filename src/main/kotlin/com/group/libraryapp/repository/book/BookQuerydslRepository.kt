package com.group.libraryapp.repository.book

import com.group.libraryapp.domain.book.QBook.book
import com.group.libraryapp.dto.book.response.BookStatResponse
import com.querydsl.core.QueryFactory
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Component

@Component
class BookQuerydslRepository(
    private val queryFactory: JPAQueryFactory,
){
    fun getStats(): List<BookStatResponse>{
        return queryFactory.select(
            Projections.constructor( //몇가지 컬럼만 가져오겠다 즉, 주어진 DTO의 값만 가져오겠다.
                BookStatResponse::class.java, // 생성자로 차례대로 들어감
                    book.type,
                    book.id.count()
            )
        ).from(book)
            .groupBy(book.type)
            .fetch()
    }
}