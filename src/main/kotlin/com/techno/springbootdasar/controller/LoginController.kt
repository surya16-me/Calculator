package com.techno.springbootdasar.controller

import com.techno.springbootdasar.domain.dto.request.ReqLoginDto
import com.techno.springbootdasar.domain.dto.request.ReqValidateLoginDto
import com.techno.springbootdasar.domain.dto.response.ResBaseDto
import com.techno.springbootdasar.service.LoginService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/v1/api")
class LoginController(
    private val loginService: LoginService
) {
    @PostMapping("/login")
    fun login(@Valid @RequestBody reqLoginDto: ReqLoginDto): ResponseEntity<ResBaseDto<Any>> {
        val response = loginService.login(reqLoginDto)
        return ResponseEntity.ok().body(response)
    }

    @PostMapping("/validate")
    fun validateUserLogin(@Valid @RequestBody reqValidateLoginDto: ReqValidateLoginDto): ResponseEntity<ResBaseDto<Any>> {
        val response = loginService.validateUserLogin(reqValidateLoginDto)

        return ResponseEntity.ok().body(response)
    }
}