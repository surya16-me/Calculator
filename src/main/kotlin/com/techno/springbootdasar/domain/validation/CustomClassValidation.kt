package com.techno.springboot.dasar.domain.validation

import com.techno.springbootdasar.domain.validation.validator.ClassValidator
import java.lang.annotation.Documented
import java.lang.annotation.ElementType.*
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass
@Documented
@Constraint(validatedBy = [ClassValidator::class])
@Target(AnnotationTarget.CLASS)
@Retention(RetentionPolicy.RUNTIME)
annotation class CustomClassValidation(
    val message: String = "Invalid ReqUser",
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<out Payload>> = []
)