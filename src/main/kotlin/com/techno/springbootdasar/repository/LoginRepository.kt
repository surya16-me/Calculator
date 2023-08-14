package com.techno.springbootdasar.repository

import com.techno.springbootdasar.domain.entity.TokenEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface LoginRepository : JpaRepository<TokenEntity, String> {
    fun findIdByToken(token: String) : TokenEntity?
    fun findById(id: UUID) : TokenEntity?
}