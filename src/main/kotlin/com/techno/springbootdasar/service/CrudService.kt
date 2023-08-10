package com.techno.springbootdasar.service

import com.techno.springbootdasar.domain.dto.request.ReqMahasiswaDto
import com.techno.springbootdasar.domain.dto.response.ResBaseDto
import com.techno.springbootdasar.domain.dto.response.ResMahasiswaDto

interface CrudService {
    fun getAll() : ResBaseDto<ArrayList<ResMahasiswaDto>>
    fun getById(id: Long) : ResBaseDto<ResMahasiswaDto>
    fun insert(reqMahasiswaDto: ReqMahasiswaDto): ResBaseDto<Any>
    fun update(reqMahasiswaDto: ReqMahasiswaDto, id: Long) : ResBaseDto<Any>
    fun delete(id: Long) : ResBaseDto<Any>
    fun insertProdi(nama: String): ResBaseDto<Any>

}