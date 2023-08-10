package com.techno.springbootdasar.controller

import com.techno.springbootdasar.domain.dto.request.ReqMahasiswaDto
import com.techno.springbootdasar.domain.dto.request.ReqProdiDto
import com.techno.springbootdasar.domain.dto.response.ResBaseDto
import com.techno.springbootdasar.domain.dto.response.ResMahasiswaDto
import com.techno.springbootdasar.service.CrudService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/v1/api/crud")
class CrudController (
    private val crudService: CrudService
){
    @GetMapping("/mahasiswa")
    fun getAll(): ResponseEntity<ResBaseDto<ArrayList<ResMahasiswaDto>>> {
        val response = crudService.getAll()
        return ResponseEntity.ok().body(response)
    }

    @GetMapping("/mahasiswa/{id}")
    fun getById(@PathVariable("id") id: Long): ResponseEntity<ResBaseDto<ResMahasiswaDto>> {
        val response = crudService.getById(id)
        return ResponseEntity.ok().body(response)
    }

    @PostMapping("/mahasiswa")
    fun insert(@RequestBody reqMahasiswaDto: ReqMahasiswaDto): ResponseEntity<ResBaseDto<Any>> {
        val response = crudService.insert(reqMahasiswaDto)
        return ResponseEntity.ok().body(response)
    }

    @PutMapping("/mahasiswa/{id}")
    fun update(@PathVariable("id") id : Long, @RequestBody reqMahasiswaDto: ReqMahasiswaDto): ResponseEntity<ResBaseDto<Any>> {
        val response = crudService.update(reqMahasiswaDto, id)
        return ResponseEntity.ok().body(response)
    }

    @DeleteMapping("/mahasiswa/{id}")
    fun delete(@PathVariable("id") id: Long): ResponseEntity<ResBaseDto<Any>> {
        val response = crudService.delete(id)
        return ResponseEntity.ok().body(response)
    }

    @PostMapping("/prodi")
    fun insertProdi(@RequestBody reqProdiDto: ReqProdiDto): ResponseEntity<ResBaseDto<Any>> {
        val response = crudService.insertProdi(reqProdiDto.nama!!)
        return ResponseEntity.ok().body(response)
    }


}