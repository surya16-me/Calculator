package com.techno.springbootdasar.repository

import com.techno.springbootdasar.domain.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface UserRepository : JpaRepository<UserEntity, String>{
    fun findByEmail(email: String): UserEntity?
    fun findById(id: UUID): UserEntity?
    fun deleteById(id: UUID)
}