package com.api.v3.medical_appointments.services

import com.api.v3.medical_appointments.domain.MedicalAppointmentRepository
import com.api.v3.medical_appointments.dtos.MedicalAppointmentResponseDto
import com.api.v3.medical_appointments.utils.MedicalAppointmentFinderUtil
import com.api.v3.medical_appointments.utils.MedicalAppointmentResponseMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service

@Service
class MedicalAppointmentRetrievalServiceImpl(
    private val medicalAppointmentRepository: MedicalAppointmentRepository,
    private val medicalAppointmentFinderUtil: MedicalAppointmentFinderUtil
): MedicalAppointmentRetrievalService {

    override suspend fun findById(id: String): MedicalAppointmentResponseDto {
        return withContext(Dispatchers.IO) {
            val foundMedicalMedicalAppointment = medicalAppointmentFinderUtil.findById(id)
            MedicalAppointmentResponseMapper.map(foundMedicalMedicalAppointment)
        }
    }

    override suspend fun findAll(): Flow<MedicalAppointmentResponseDto> {
        return withContext(Dispatchers.IO) {
            medicalAppointmentRepository
                .findAll()
                .map { ma -> MedicalAppointmentResponseMapper.map(ma) }
        }
    }
}