package com.techno.springbootdasar.domain.dto.request

import javax.validation.constraints.NotNull

data class ReqProdiDto(
    @field:NotNull(message = "Nama prodi harus diisi")
    val nama : String? = null
)
