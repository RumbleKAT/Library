package com.group.libraryapp.domain.user

import com.group.libraryapp.domain.user.QUser.user
import com.group.libraryapp.domain.user.loanhistory.QUserLoanHistory.userLoanHistory
import com.querydsl.jpa.impl.JPAQueryFactory

class UserRepositoryCustomImpl(
    private val queryFactory: JPAQueryFactory
): UserRepositoryCustom {
    override fun findAllWithHistories(): List<User> {
        return queryFactory.select(user) //select *
            .distinct() //distinct
            .from(user) // from user
            .leftJoin(userLoanHistory).on(userLoanHistory.user.id.eq(user.id)).fetchJoin() // join 앞에 fetch를 fetch join으로 인식한다.
            .fetch()
    }
}