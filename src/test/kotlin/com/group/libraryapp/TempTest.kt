package com.group.libraryapp

import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.service.user.UserService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class TempTest @Autowired constructor(
    private val userService: UserService,
    private val userRepository: UserRepository,
    private val txHelper: TxHelper,
){

    @Test
    fun test(){
        //when
        userService.saveUserAndLoanTwoBooks()

        txHelper.exec {
            //then 미리 선언한 람다로 처리
            val users = userRepository.findAllWithHistories()
            assertThat(users).hasSize(1)
            assertThat(users[0].userLoanHistories).hasSize(2) // 테스트코드가 지연초기화를 할수없음
        }
    }


}