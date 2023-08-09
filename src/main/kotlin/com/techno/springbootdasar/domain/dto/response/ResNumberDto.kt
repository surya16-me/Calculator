package com.techno.springbootdasar.domain.dto.response

import com.fasterxml.jackson.annotation.JsonProperty

data class ResNumberDto(
    @field:JsonProperty(value = "value_a")
    val valueA : Double? = null,

    @field:JsonProperty(value = "value_b")
    val valueB : Double? = null,

    @field:JsonProperty(value = "result")
    val valueResult : Double? = null,

)
