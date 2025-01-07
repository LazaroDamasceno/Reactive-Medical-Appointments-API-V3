package com.api.v3.doctors.services

import com.api.v3.doctors.domain.DoctorRepository
import com.api.v3.doctors.dtos.DoctorResponseDto
import com.api.v3.doctors.utils.DoctorFinderUtil
import com.api.v3.doctors.utils.DoctorResponseMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service

@Service
class DoctorRetrievalServiceImpl(
    private val doctorFinderUtil: DoctorFinderUtil,
    private val doctorRepository: DoctorRepository
): DoctorRetrievalService {

    override suspend fun findByMedicalLicenseNumber(medicalLicenseNumber: String): DoctorResponseDto {
        return withContext(Dispatchers.IO) {
            val foundDoctor = doctorFinderUtil.findByMedicalLicenseNumber(medicalLicenseNumber)
            DoctorResponseMapper.map(foundDoctor)
        }
    }

    override suspend fun findAll(): Flow<DoctorResponseDto> {
        return withContext(Dispatchers.IO) {
            doctorRepository
                .findAll()
                .map { d -> DoctorResponseMapper.map(d) }
        }
    }
}