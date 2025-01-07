package com.api.v3.medical_appointments.dtos

import com.api.v3.customers.dtos.CustomerResponseDto
import com.api.v3.doctors.dtos.DoctorResponseDto
import java.time.LocalDateTime
import java.time.ZoneId

data class MedicalAppointmentResponseDto(
    val customerResponseDto: CustomerResponseDto,
    val doctorResponseDto: DoctorResponseDto,
    val bookedAt: LocalDateTime,
    val bookedAtZone: ZoneId,
    val canceledAt: LocalDateTime?,
    val canceledAtZone: ZoneId?,
    val completedAt: LocalDateTime?,
    val completedAtZone: ZoneId?,
)
