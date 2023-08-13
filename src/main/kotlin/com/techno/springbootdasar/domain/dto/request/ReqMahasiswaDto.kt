package com.techno.springbootdasar.domain.dto.request

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

data class ReqMahasiswaDto(


    @field:NotNull(message = "NIm tidak boleh null")
    val nim: Long? = null,

    @field:NotNull(message = "Nama tidak boleh kosong")
    val nama: String? = null,

    @field:NotNull(message = "Gender tidak boleh kosong")
    val gender: String? = null,

    @field:NotNull(message = "Alamat tidak boleh kosong")
    val alamat: String? = null,

    @field:NotNull(message = "Prodi tidak boleh kosong")
    val idProdi : String? = null
)
