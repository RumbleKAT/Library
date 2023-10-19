package com.group.libraryapp.domain.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface UserRepository: JpaRepository<User,Long> {
    fun findByName(name: String): User?

//    @Query("SELECT u FROM User u LEFT JOIN u.userLoanHistories") 실제 User 안에 넣을때 FETCH가 필요하다.
    @Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.userLoanHistories")
    fun findAllWithHistories(): List<User>
}