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
        var expiredJwt = System.currentTimeMillis()+3600000L
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
        val user = mapOf(
            "id" to data.idUser!!.id,
            "name" to data.idUser!!.name,
            "username" to data.idUser!!.username,
            "email" to data.idUser!!.email
        )
        return ResBaseDto(data = user)
    }

}
