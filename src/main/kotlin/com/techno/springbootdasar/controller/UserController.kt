package com.techno.springbootdasar.controller

import com.techno.springbootdasar.domain.dto.request.ReqUserDto
import com.techno.springbootdasar.domain.dto.response.ResBaseDto
import com.techno.springbootdasar.domain.dto.response.ResUserDto
import com.techno.springbootdasar.domain.validation.validator.FieldValidator
import com.techno.springbootdasar.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID
import javax.validation.Valid

@RestController
@RequestMapping("/v1/api/crud")
class UserController(
    private val userService: UserService,
    private val fieldValidator: FieldValidator
) {
    @GetMapping("/user")
    fun getAll(): ResponseEntity<ResBaseDto<ArrayList<ResUserDto>>> {
        val res = userService.getAllUsers()
        return ResponseEntity.ok().body(res)
    }
    @GetMapping("/user/{uuid}")
    fun getById(@PathVariable("uuid") id : UUID): ResponseEntity<ResBaseDto<ResUserDto>> {
        val res = userService.getUserById(id)
        return ResponseEntity.ok().body(res)
    }
    @PostMapping("/user")
    fun insert(@Valid @RequestBody reqUserDto: ReqUserDto): ResponseEntity<ResBaseDto<Any>> {

        val response = userService.insertUser(reqUserDto)
        return ResponseEntity.ok().body(response)
    }
    @PutMapping("/user/{uuid}")
    fun update(@PathVariable("uuid")id : UUID, @RequestBody reqUserDto: ReqUserDto): ResponseEntity<ResBaseDto<Any>> {
        val res = userService.updateUser(reqUserDto, id)
        return ResponseEntity.ok().body(res)
    }

    @DeleteMapping("/user/{uuid}")
    fun delete(@PathVariable("uuid")id : UUID): ResponseEntity<ResBaseDto<Any>> {
        val res = userService.deleteUser(id)
        return ResponseEntity.ok().body(res)
    }
}