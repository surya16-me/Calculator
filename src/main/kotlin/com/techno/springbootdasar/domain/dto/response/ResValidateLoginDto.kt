package com.techno.springbootdasar.domain.dto.response

import java.util.*

data class ResValidateLoginDto(
    val id : UUID? = null,
    val email : String? = null,
    val name : String? = null,
    val username : String? = null
)
