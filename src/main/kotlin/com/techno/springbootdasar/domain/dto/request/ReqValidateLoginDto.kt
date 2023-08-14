package com.techno.springbootdasar.domain.dto.request

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class ReqValidateLoginDto(
    @field:NotNull(message = "Token can't be null")
    @field:NotEmpty(message = "Token can't be empty")
    val token : String? = null
)
