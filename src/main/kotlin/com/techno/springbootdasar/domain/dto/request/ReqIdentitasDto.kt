package com.techno.springbootdasar.domain.dto.request

import com.fasterxml.jackson.annotation.JsonProperty

data class ReqIdentitasDto(

    @field:JsonProperty(value = "first_name")
    val firstName : String? = null,

    @field:JsonProperty(value = "last_name")
    val lastName : String? = null,

    @field:JsonProperty(value = "age")
    val age : Long? = null
)
