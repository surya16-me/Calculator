package com.techno.springbootdasar.service.impl

import com.techno.springbootdasar.domain.dto.request.ReqIdentitasDto
import com.techno.springbootdasar.domain.dto.request.ReqNumberDto
import com.techno.springbootdasar.domain.dto.response.ResBaseDto
import com.techno.springbootdasar.domain.dto.response.ResFullNameDto
import com.techno.springbootdasar.domain.dto.response.ResNumberDto
import com.techno.springbootdasar.service.LogicService
import org.springframework.stereotype.Service

@Service
class LogicServiceImpl : LogicService {
    override fun PrintName(name: String) {
        println("My name is $name")
    }

    override fun OddsOrEvent(number: Int): String {
        return if(number%2 == 0 ){
            "Event"
        }else{
            "Odds"
        }
    }

    override fun fullName(reqIdentitasDto: ReqIdentitasDto): ResBaseDto<ResFullNameDto> {
        val fullNameTemp = reqIdentitasDto.firstName + " " + reqIdentitasDto.lastName
        val resFullName = ResFullNameDto(
            firstName = reqIdentitasDto.firstName,
            lastName = reqIdentitasDto.lastName,
            fullName = fullNameTemp
        )

        return ResBaseDto(
            data = resFullName
        )
    }

    override fun countNumber(reqNumberDto: ReqNumberDto, operation: String): ResBaseDto<ResNumberDto> {
        val resultTemp: Double?
        val status: Boolean
        val message: String

        when (operation) {
            "addition" -> resultTemp = reqNumberDto.valueA?.plus(reqNumberDto.valueB!!)
            "subtraction" -> resultTemp = reqNumberDto.valueA?.minus(reqNumberDto.valueB!!)
            "multiple" -> resultTemp = reqNumberDto.valueA?.times(reqNumberDto.valueB!!)
            "division" -> {
                if (reqNumberDto.valueB != 0.0) {
                    resultTemp = reqNumberDto.valueA?.div(reqNumberDto.valueB!!)
                } else {
                    resultTemp = null
                }
            }
            else -> throw IllegalArgumentException("Invalid operation: $operation")
        }

        val resNumberDto = ResNumberDto(
            valueA = reqNumberDto.valueA,
            valueB = reqNumberDto.valueB,
            valueResult = resultTemp
        )

        if (resultTemp != null) {
            status = true
            message = "Calculation successful"
        } else {
            status = false
            message = "Invalid operation or division by zero"
        }

        return ResBaseDto(
            status = status,
            message = message,
            data = resNumberDto
        )
    }


}