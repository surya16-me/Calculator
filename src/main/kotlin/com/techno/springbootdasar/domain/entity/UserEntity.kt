package com.techno.springbootdasar.domain.entity

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "users")
data class UserEntity (
    @field:Id
    @field:Column(name ="uuid", columnDefinition = "uuid")
    val id : UUID? = null,

    @field:Column(name = "name", columnDefinition = "varchar")
    val name : String? = null,

    @field:Column(name = "username", columnDefinition = "varchar")
    val username : String? = null,

    @field:Column(name = "email", columnDefinition = "varchar")
    val email : String? = null,

    @field:Column(name = "password", columnDefinition = "varchar")
    val password : String? = null
)