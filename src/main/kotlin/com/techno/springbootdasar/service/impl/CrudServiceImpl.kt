package com.techno.springbootdasar.service.impl

import com.techno.springbootdasar.domain.dto.request.ReqMahasiswaDto
import com.techno.springbootdasar.domain.dto.response.ResBaseDto
import com.techno.springbootdasar.domain.dto.response.ResMahasiswaDto
import com.techno.springbootdasar.domain.dto.response.ResProdiDto
import com.techno.springbootdasar.domain.entity.MahasiswaEntity
import com.techno.springbootdasar.domain.entity.ProdiEntity
import com.techno.springbootdasar.repository.MahasiswaRepository
import com.techno.springbootdasar.repository.ProdiRepository
import com.techno.springbootdasar.service.CrudService
import org.springframework.stereotype.Service
import java.util.*
import kotlin.collections.ArrayList

@Service
class CrudServiceImpl (
    private val mahasiswaRepository: MahasiswaRepository,
    private val prodiRepository: ProdiRepository
): CrudService {
    override fun getAll(): ResBaseDto<ArrayList<ResMahasiswaDto>> {
        val data = mahasiswaRepository.findAll()
        if (data.isEmpty()){
            return ResBaseDto(false, "Data not found", null)
        }
        val  response : ArrayList<ResMahasiswaDto> = ArrayList()
        data.forEach {
            response.add(
                ResMahasiswaDto(
                    nim = it.nim!!,
                    nama = it.nama!!,
                    gender = it.gender!!,
                    alamat = it.alamat!!,
                    jurusan = it.idProdi!!.namaProdi!!
                )
            )
        }
        return ResBaseDto(data = response)
    }

    override fun getById(id: Long): ResBaseDto<ResMahasiswaDto> {
        val data = mahasiswaRepository.findById(id) ?: return ResBaseDto(false, "Data not found", null)
        val response = ResMahasiswaDto(
            nim = data.nim!!,
            nama = data.nama!!,
            gender = data.gender!!,
            alamat = data.alamat!!,
            jurusan = data.idProdi!!.namaProdi!!
        )
        return ResBaseDto(data = response)
    }

    override fun insert(reqMahasiswaDto: ReqMahasiswaDto): ResBaseDto<Any> {
        val prodiEntity = prodiRepository.findById(UUID.fromString(reqMahasiswaDto.idProdi)) ?: return ResBaseDto(false, "Data not Found", null)
        val data = MahasiswaEntity(
            nim = reqMahasiswaDto.nim,
            nama = reqMahasiswaDto.nama,
            gender = reqMahasiswaDto.gender,
            alamat = reqMahasiswaDto.alamat,
            idProdi = prodiEntity
        )

        val entity = mahasiswaRepository.save(data)
        val response = ResMahasiswaDto(
            nama = entity.nama!!,
            nim = entity.nim!!,
            gender = entity.gender!!,
            alamat = entity.alamat!!,
            jurusan = entity.idProdi!!.namaProdi!!
        )
        return ResBaseDto(data = response)
    }



    override fun update(reqMahasiswaDto: ReqMahasiswaDto, id: Long): ResBaseDto<Any> {
        val prodiEntity = prodiRepository.findById(UUID.fromString(reqMahasiswaDto.idProdi)) ?: return ResBaseDto(false, "Data not Found", null)
        val data = mahasiswaRepository.findById(id) ?: return ResBaseDto(false, "Data not found")
        val newData = data.copy(
            nim = reqMahasiswaDto.nim,
            nama = reqMahasiswaDto.nama,
            gender = reqMahasiswaDto.gender,
            alamat = reqMahasiswaDto.alamat,
            idProdi = prodiEntity
        )
        val entity = mahasiswaRepository.save(newData)
        val response = ResMahasiswaDto(
            nama = entity.nama!!,
            nim = entity.nim!!,
            gender = entity.gender!!,
            alamat = entity.alamat!!,
            jurusan = entity.idProdi!!.namaProdi!!
        )
        return ResBaseDto(data = response)
    }

    override fun delete(id: Long): ResBaseDto<Any> {
        mahasiswaRepository.deleteId(id)
        return ResBaseDto(data = null)
    }

    override fun insertProdi(nama: String): ResBaseDto<Any> {
        val uuid = UUID.randomUUID()
        val data = ProdiEntity(
            id = uuid,
            namaProdi = nama
        )
        val entity = prodiRepository.save(data)
        val response = ResProdiDto(
            nama = entity.namaProdi!!
        )
        return ResBaseDto(data = response)
    }


}