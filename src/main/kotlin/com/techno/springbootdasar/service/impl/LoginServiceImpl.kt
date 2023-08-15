package com.techno.springbootdasar.service.impl

import com.techno.springbootdasar.domain.dto.request.ReqLoginDto
import com.techno.springbootdasar.domain.dto.request.ReqValidateLoginDto
import com.techno.springbootdasar.domain.dto.response.ResBaseDto
import com.techno.springbootdasar.domain.entity.TokenEntity
import com.techno.springbootdasar.exception.CustomExceptionHandler
import com.techno.springbootdasar.repository.LoginRepository
import com.techno.springbootdasar.repository.UserRepository
import com.techno.springbootdasar.service.LoginService
import com.techno.springbootdasar.util.JWTGenerator
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*

@Service
class LoginServiceImpl(
    private val userRepository: UserRepository,
    private val loginRepository: LoginRepository
) : LoginService{
    override fun login(reqLoginDto: ReqLoginDto): ResBaseDto<Any> {
        val data = userRepository.findByUsername(reqLoginDto.username.toString()) ?: throw CustomExceptionHandler("Username not found!!")
        if (reqLoginDto.password != data.password)
            throw CustomExceptionHandler("Invalid username or password!!")
        var expiredJwt = System.currentTimeMillis()+60000L
        val expiredDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(expiredJwt), ZoneId.systemDefault())
        val token : String? = JWTGenerator().createJWT(
            reqLoginDto.username!!,
            reqLoginDto.password!!,
            expiredJwt
        )
        val dataEntity = TokenEntity(
            id = UUID.randomUUID(),
            token = token,
            exp = expiredDate,
            idUser = data,
        )
        loginRepository.save(dataEntity)
        return ResBaseDto(data = mapOf("token" to token))
    }

    override fun validateUserLogin(reqValidateLoginDto: ReqValidateLoginDto): ResBaseDto<Any> {
        val data = loginRepository.findIdByToken(reqValidateLoginDto.token.toString()) ?: throw CustomExceptionHandler("Token not found or isn't valid anymore")
        println(data.exp)
//        val expirationMillis = data.exp?.toInstant(ZoneOffset.UTC)?.toEpochMilli() ?: throw CustomExceptionHandler("Token expiration not available")
//
//        val currentMillis = System.currentTimeMillis()
//
//        if (currentMillis > expirationMillis) {
//            throw CustomExceptionHandler("Token has expired")
//        }
        val isTokenValid = validateToken(reqValidateLoginDto.token.toString())

        val user = mapOf(
            "id" to data.idUser!!.id,
            "name" to data.idUser!!.name,
            "username" to data.idUser!!.username,
            "email" to data.idUser!!.email
        )
        return ResBaseDto(data = user)
    }

    override fun validateToken(auth: String): Boolean {
        val data = loginRepository.findIdByToken(auth) ?: throw CustomExceptionHandler("Token not valid")
        val dateTimeString = data.exp.toString()
        val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS")
        val dateTime = LocalDateTime.parse(dateTimeString, dateTimeFormatter)
        val currentTime = LocalDateTime.now()
        if (currentTime.isAfter(dateTime)){
            throw CustomExceptionHandler("Token expired")
        }
        return true
    }


}
