package com.api.v3.medical_appointments.services

import com.api.v3.medical_appointments.dtos.MedicalAppointmentResponseDto
import kotlinx.coroutines.flow.Flow

interface MedicalAppointmentRetrievalService {
    suspend fun findById(id: String): MedicalAppointmentResponseDto
    suspend fun findAll(): Flow<MedicalAppointmentResponseDto>
}