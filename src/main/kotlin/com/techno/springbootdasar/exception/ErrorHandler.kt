package com.techno.springbootdasar.exception

import com.techno.springbootdasar.domain.dto.response.ResBaseDto
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.lang.RuntimeException
import kotlin.Exception

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
class ErrorHandler {

    @ExceptionHandler(Exception::class)
    fun handlerException(exception: Exception): ResponseEntity<ResBaseDto<String>> {
        println("ERROR GENERAL")
        exception.printStackTrace()
        val response = (ResBaseDto(false, "Something went wrong", null, 400, exception.message))
        return ResponseEntity.badRequest().body(response)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handlerArgumentNotValidException(exception:
                                         MethodArgumentNotValidException): ResponseEntity<Any>{
        val errors = mutableListOf<String>()
        exception.bindingResult.fieldErrors.forEach {
            errors.add(it.defaultMessage!!)
        }
        val response = (ResBaseDto(false,"Something Went Wrong",null, 400,errors))
        return ResponseEntity.badRequest().body(response)
    }
    @ExceptionHandler(CustomExceptionHandler::class)
    fun handleCustomException(exception: RuntimeException): ResponseEntity<Any>{
        val response = ResBaseDto(false,exception.message!!, null, 400, "")
        return ResponseEntity.badRequest().body(response)
    }

}