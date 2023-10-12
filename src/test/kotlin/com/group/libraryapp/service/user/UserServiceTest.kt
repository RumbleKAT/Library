package com.group.libraryapp.service.user

import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.dto.user.request.UserCreateRequest
import com.group.libraryapp.dto.user.request.UserUpdateRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserServiceTest @Autowired constructor(
    private val userRepository: UserRepository,
    private val userService: UserService
){

    @Test
    @DisplayName("유저 저장이 정상 동작한다.")
    fun saveUserTest(){
        // given
        val request = UserCreateRequest("송명진", null)

        // when
        userService.saveUser(request)

        // then
        val results = userRepository.findAll()
        assertThat(results).hasSize(1)
        assertThat(results[0].name).isEqualTo("송명진")
        assertThat(results[0].age).isNull() //Integer의 null 여부를 kotlin에서 확인할 수 없음
    }

    @Test
    fun getUsersTest(){
        // given
        userRepository.saveAll(listOf(User("A",20), User("B",null)))

        // when
        val results = userService.getUsers()

        // then
        assertThat(results).hasSize(2)
        assertThat(results).extracting("name").containsExactlyInAnyOrder("A","B")
        assertThat(results).extracting("age").containsExactlyInAnyOrder(20,null)
    }


    @AfterEach
    fun clean(){
        userRepository.deleteAll()
    }

    @Test
    fun updateUserNameTest(){
        // given
        val savedUser = userRepository.save(User("A",null))
        val request = UserUpdateRequest(savedUser.id!!, "B")
        // when
        userService.updateUserName(request)
        // then
        val result = userRepository.findAll()[0]
        assertThat(result.name).isEqualTo("B")
    }

    @Test
    fun deleteUserTest(){
        // given
        userRepository.save(User("A",null))
        // when
        userService.deleteUser("A")
        // then
        assertThat(userRepository.findAll()).isEmpty()
    }

}