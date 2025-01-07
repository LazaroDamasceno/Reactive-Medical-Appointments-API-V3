package com.api.v3.medical_slots.services

import com.api.v3.medical_slots.dtos.MedicalSlotRegistrationDto
import com.api.v3.medical_slots.dtos.MedicalSlotResponseDto

interface MedicalSlotRegistrationService {
    suspend fun register(registrationDto: MedicalSlotRegistrationDto): MedicalSlotResponseDto
}