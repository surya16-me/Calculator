package com.techno.springbootdasar.domain.dto.request

data class ReqMahasiswaDto(
    val nim: Long? = null,
    val nama: String? = null,
    val gender: String? = null,
    val alamat: String? = null,
    val idProdi : String? = null
)
