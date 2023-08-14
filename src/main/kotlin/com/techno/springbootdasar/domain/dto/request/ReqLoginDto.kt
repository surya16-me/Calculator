package com.techno.springbootdasar.domain.dto.request

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class ReqLoginDto(
    @field:NotNull(message = "Username can't be null")
    @field:NotEmpty(message = "Username can't be empty")
    val username : String? = null,

    @field:NotNull(message = "Password can't be null")
    @field:NotEmpty(message = "Password can't be empty")
    val password : String? = null
)
