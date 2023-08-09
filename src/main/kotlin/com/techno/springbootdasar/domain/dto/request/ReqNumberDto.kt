package com.techno.springbootdasar.domain.dto.request

import com.fasterxml.jackson.annotation.JsonProperty

data class ReqNumberDto(
    @field:JsonProperty(value = "value_a")
    val valueA : Double? = null,

    @field:JsonProperty(value = "value_b")
    val valueB : Double? = null,

    )
