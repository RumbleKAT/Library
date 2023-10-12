package com.group.libraryapp.calculator

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class CalculatorTest {

    //@BeforeAll과 @AfterAll은 JvmStatic을 써야됨

    @Test
    fun add() {
        // given
        val calculator = Calculator(5)

        // when
        calculator.add(3)

        // then
        if(calculator.number != 8){
            throw IllegalStateException()
        }
    }

    @Test
    fun minus() {
        // given
        val calculator = Calculator(5)

        // when
        calculator.minus(3)

        // then
        if(calculator.number != 2){
            throw IllegalStateException()
        }
    }

    @Test
    fun multiply() {
        // given
        val calculator = Calculator(5)

        // when
        calculator.multiply(3)

        // then
        if(calculator.number != 15){
            throw IllegalStateException()
        }
    }

    @Test
    fun divide() {
        // given
        val calculator = Calculator(5)

        // when
        calculator.divide(2)

        // then
        if(calculator.number != 2){
            throw IllegalStateException()
        }
    }

    @Test
    fun divideException(){
        // given
        val calculator = Calculator(5)

        // when
        try{
            calculator.divide(0)
        }catch (e: IllegalArgumentException){
            if(e.message != "0으로 나눌 수 없습니다.")  throw IllegalStateException("메시지가 다릅니다.")
            // 테스트 성공 !!
            return
        }catch (e : Exception){
            throw IllegalStateException()
        }
        throw IllegalStateException("기대하는 예외가 발생하지 않았습니다.")
    }

}