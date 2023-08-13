package com.techno.springbootdasar.service.impl

import com.techno.springbootdasar.domain.dto.request.ReqUserDto
import com.techno.springbootdasar.domain.dto.response.ResBaseDto
import com.techno.springbootdasar.domain.dto.response.ResUserDto
import com.techno.springbootdasar.domain.entity.UserEntity
import com.techno.springbootdasar.domain.validation.validator.FieldValidator
import com.techno.springbootdasar.exception.CustomExceptionHandler
import com.techno.springbootdasar.repository.UserRepository
import com.techno.springbootdasar.service.UserService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.validation.annotation.Validated
import java.util.*
import kotlin.collections.ArrayList

@Service
@Validated
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val fieldValidator: FieldValidator
) : UserService {
    override fun getAllUsers(): ResBaseDto<ArrayList<ResUserDto>> {
        val data: MutableList<UserEntity> = userRepository.findAll()
        if (data.isEmpty())
            throw CustomExceptionHandler("Data Not Found")
        val res : ArrayList<ResUserDto> = ArrayList()
        data.forEach {
            res.add(
                ResUserDto(
                    id = it.id!!,
                    name = it.name!!,
                    username = it.username!!,
                    email = it.email!!,
                    password = it.password!!
                )
            )
        }
        return ResBaseDto(data = res)
    }

    override fun getUserById(id: UUID): ResBaseDto<ResUserDto> {
        val data = userRepository.findById(id) ?: throw CustomExceptionHandler("Data Not Found")
        val res = ResUserDto(
            id = data.id!!,
            name = data.name!!,
            username = data.username!!,
            email = data.email!!,
            password = data.password!!
        )
        return ResBaseDto(data = res)
    }

    override fun insertUser(reqUserDto: ReqUserDto): ResBaseDto<Any> {
        val existingUser = userRepository.findByEmail(reqUserDto.email.toString())
        if (existingUser != null) {
            throw CustomExceptionHandler("Email already used!")
        }
        val uuid = UUID.randomUUID()
        val data = UserEntity(
            id = uuid,
            name = reqUserDto.name!!,
            username = reqUserDto.username!!,
            email = reqUserDto.email!!,
            password = reqUserDto.password
        )

        val entity = userRepository.save(data)
        val res = ResUserDto(
            id = entity.id,
            name = entity.name,
            username = entity.username,
            email = entity.email,
            password = entity.password,
        )
        return ResBaseDto(data = res)
    }

    override fun updateUser(reqUserDto: ReqUserDto, id: UUID): ResBaseDto<Any> {
        val data = userRepository.findById(id) ?: throw CustomExceptionHandler("Data Not Found")
        val newData = data.copy(
            name = reqUserDto.name,
            username = reqUserDto.username,
            email = reqUserDto.email,
            password = reqUserDto.password,
        )
        val entity = userRepository.save(newData)
        val response = ResUserDto(
            id = entity.id!!,
            name = entity.name!!,
            username = entity.username!!,
            email = entity.username!!,
            password = entity.password!!,
        )
        return ResBaseDto(data = response)
    }

    @Transactional
    override fun deleteUser(id: UUID): ResBaseDto<Any> {

        userRepository.deleteById(id)
        return ResBaseDto(data = null)
    }
}