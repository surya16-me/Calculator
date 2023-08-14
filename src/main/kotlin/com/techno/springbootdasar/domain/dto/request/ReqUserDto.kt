package com.techno.springbootdasar.domain.dto.request

import com.techno.springbootdasar.domain.validation.CustomFieldValidation
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

data class ReqUserDto(
    @field:NotNull(message = "Field Name Mahasiswa Can't be Null!!")
    @field:NotEmpty(message = "Field Name Mahasiswa Can't be Empty!!")
    val name: String? = null,

    @field:NotNull(message = "Field Username Can't be Null!!")
    @field:NotEmpty(message = "Field Username Can't be Empty!!")
    @field:Size(max = 32, message = "The field length must be at most 32 characters.")
    val username: String? = null,

    @field:NotNull(message = "Field email Can't be Null!!")
    @field:NotEmpty(message = "Field email Can't be Empty!!")
    @field:Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "Invalid email format!!")
    @field:CustomFieldValidation(message = "Email already taken!!")
    val email: String? = null,

    @field:NotNull(message = "Field Password Can't be Null!!l")
    @field:NotEmpty(message = "Field Password Can't be Empty!!")
    @field:Size(max = 32, message = "The field length must be at most 32 characters.")
    val password: String? = null

)
