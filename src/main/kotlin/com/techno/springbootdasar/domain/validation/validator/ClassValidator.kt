package com.techno.springbootdasar.domain.validation.validator

import com.techno.springbootdasar.domain.validation.CustomClassValidation
import com.techno.springbootdasar.domain.dto.request.ReqUserDto
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class ClassValidator: ConstraintValidator<CustomClassValidation, ReqUserDto>{
    override fun isValid(value: ReqUserDto?, context: ConstraintValidatorContext?): Boolean {
        if (value == null) {
            return false
        }
        return !value.name.isNullOrEmpty() && !value.username.isNullOrEmpty() && !value.email.isNullOrEmpty() && !value.password.isNullOrEmpty()
    }
}