package com.api.v3.doctors.services

import com.api.v3.doctors.dtos.DoctorResponseDto
import kotlinx.coroutines.flow.Flow

interface DoctorRetrievalService {
    suspend fun findByMedicalLicenseNumber(medicalLicenseNumber: String): DoctorResponseDto
    suspend fun findAll(): Flow<DoctorResponseDto>
}