package com.techno.springbootdasar.service

import com.techno.springbootdasar.domain.dto.request.ReqIdentitasDto
import com.techno.springbootdasar.domain.dto.request.ReqNumberDto
import com.techno.springbootdasar.domain.dto.response.ResBaseDto
import com.techno.springbootdasar.domain.dto.response.ResFullNameDto
import com.techno.springbootdasar.domain.dto.response.ResNumberDto

interface LogicService {
    fun PrintName(name: String)
    fun OddsOrEvent(number: Int) : String
    fun fullName(reqIdentitasDto: ReqIdentitasDto) : ResBaseDto<ResFullNameDto>
    fun countNumber(reqNumberDto: ReqNumberDto, operation: String): ResBaseDto<ResNumberDto>
}