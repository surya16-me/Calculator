package com.techno.springbootdasar.config

import com.techno.springbootdasar.service.LogicService
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class ContohConfig(
    private val logicService: LogicService
) {

    @Bean
    fun PrintName(){
        logicService.PrintName("Surya Aji Pratama")
    }

    @Bean
    fun getOddsorEvent(){
        val result : String = logicService.OddsOrEvent(9)
        println("Number result is $result")
    }
}