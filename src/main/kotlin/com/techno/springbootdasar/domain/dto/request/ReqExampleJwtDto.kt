package com.techno.springbootdasar.domain.dto.request

data class ReqExampleJwtDto(
    val token: String? = null,
    val id: Int? = null,
    val username : String? = null,
    val password : String? = null
)
