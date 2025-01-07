package com.api.v3.medical_slots.dtos

import com.api.v3.doctors.dtos.DoctorResponseDto
import java.time.LocalDateTime
import java.time.ZoneId

data class MedicalSlotResponseDto(
    val doctorResponseDto: DoctorResponseDto,
    val availableAt: LocalDateTime,
    val availableAtZone: ZoneId,
    val canceledAt: LocalDateTime?,
    val canceledAtZone: ZoneId?,
    val completedAt: LocalDateTime?,
    val completedAtZone: ZoneId?
)
