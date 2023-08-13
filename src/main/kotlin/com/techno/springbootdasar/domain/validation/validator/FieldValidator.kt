package com.techno.springbootdasar.domain.validation.validator

import com.techno.springboot.dasar.domain.validation.CustomFieldValidation
import com.techno.springbootdasar.repository.UserRepository
import org.springframework.stereotype.Component
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

@Component
class FieldValidator(
    private val userRepository: UserRepository
): ConstraintValidator<CustomFieldValidation, String> {
    override fun isValid(p0: String?, p1: ConstraintValidatorContext?): Boolean {
        if (p0 == null){
            return true
        }
        val data = userRepository.findAll()
        data.forEach {
            if (it.username == p0 || it.email == p0 )
                return false
        }
        return true
    }
}