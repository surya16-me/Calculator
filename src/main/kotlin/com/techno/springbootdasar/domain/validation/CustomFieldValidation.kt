package com.techno.springboot.dasar.domain.validation

import com.techno.springbootdasar.domain.validation.validator.FieldValidator
import java.lang.annotation.Documented
import java.lang.annotation.ElementType.*
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Documented
@Constraint(validatedBy = [FieldValidator::class])
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.FIELD,
    AnnotationTarget.ANNOTATION_CLASS,
    AnnotationTarget.CONSTRUCTOR,
    AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.TYPE
)
@Retention(RetentionPolicy.RUNTIME)
annotation class CustomFieldValidation(
    val message: String = "Default Message",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<Payload>> = []
)
