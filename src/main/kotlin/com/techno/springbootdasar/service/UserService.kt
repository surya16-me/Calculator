package com.techno.springbootdasar.service

import com.techno.springbootdasar.domain.dto.request.ReqUserDto
import com.techno.springbootdasar.domain.dto.response.ResBaseDto
import com.techno.springbootdasar.domain.dto.response.ResUserDto
import java.util.UUID

interface UserService {
    fun getAllUsers() : ResBaseDto<ArrayList<ResUserDto>>
    fun getUserById(id: UUID) : ResBaseDto<ResUserDto>
    fun insertUser(reqUserDto: ReqUserDto): ResBaseDto<Any>
    fun updateUser(reqUserDto: ReqUserDto, id: UUID) : ResBaseDto<Any>
    fun deleteUser(id: UUID): ResBaseDto<Any>
}