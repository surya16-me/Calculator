package com.techno.springbootdasar.domain.dto.response

import java.util.*

data class ResUserDto(
    val id: UUID? = null,
    val name: String? = null,
    val username: String? = null,
    val email: String? = null,
    val password: String? = null
)
