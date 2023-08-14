package com.techno.springbootdasar.service

import com.techno.springbootdasar.domain.dto.request.ReqLoginDto
import com.techno.springbootdasar.domain.dto.request.ReqValidateLoginDto
import com.techno.springbootdasar.domain.dto.response.ResBaseDto

interface LoginService {
    fun login(reqLoginDto: ReqLoginDto): ResBaseDto<Any>
    fun validateUserLogin(reqValidateLoginDto: ReqValidateLoginDto): ResBaseDto<Any>
}