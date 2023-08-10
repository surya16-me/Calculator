package com.techno.springbootdasar.repository

import com.techno.springbootdasar.domain.entity.ProdiEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ProdiRepository : JpaRepository<ProdiEntity, String> {
    fun findById(id: UUID): ProdiEntity?
}
