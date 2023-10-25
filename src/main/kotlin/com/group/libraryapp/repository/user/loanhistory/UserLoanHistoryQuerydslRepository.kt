package com.group.libraryapp.repository.user.loanhistory

import com.group.libraryapp.domain.user.loanhistory.QUserLoanHistory.userLoanHistory
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory
import com.group.libraryapp.domain.user.loanhistory.UserLoanStatus
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Component

@Component
class UserLoanHistoryQuerydslRepository(
    private val queryFactory: JPAQueryFactory,
){
    fun find(bookName: String, status: UserLoanStatus? = null): UserLoanHistory?{ //default 파라미터로 전달
        return queryFactory.select(userLoanHistory)
            .from(userLoanHistory)
            .where(
                userLoanHistory.bookName.eq(bookName),
                status?.let{
                    userLoanHistory.status.eq(status) //status가 있는 경우에만 조건문을 탄다. 여러 조건으로 들어오면 and로 null이면 무시함
                }
            ).limit(1)
            .fetchOne() //Entity 하나만 리턴
    }

    fun count(status:UserLoanStatus): Long{
        return queryFactory.select(userLoanHistory.count())
            .from(userLoanHistory)
            .where(
                userLoanHistory.status.eq(status)
            )
            .fetchOne() ?: 0L
    }
}