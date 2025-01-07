package com.api.v3.medical_slots.dtos

import java.time.LocalDateTime

data class MedicalSlotRegistrationDto(
    val medicalLicenseNumber: String,
    val availableAt: LocalDateTime
)
