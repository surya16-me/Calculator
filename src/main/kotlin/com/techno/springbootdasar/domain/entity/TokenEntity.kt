package com.techno.springbootdasar.domain.entity

import java.time.LocalDateTime
import java.util.Date
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
data class TokenEntity(
    @field:Id
    @field:Column(name = "uuid", columnDefinition = "uuid")
    val id: UUID? = null,

    @field:Column(name = "token", columnDefinition = "varchar")
    val token: String? = null,

    @field:Column(name = "expired_token", columnDefinition = "timestamp")
    val exp: LocalDateTime? = null,

    @ManyToOne
    @field:JoinColumn(name = "id_user", referencedColumnName = "uuid", columnDefinition = "uuid")
    val idUser: UserEntity? = null,
)
