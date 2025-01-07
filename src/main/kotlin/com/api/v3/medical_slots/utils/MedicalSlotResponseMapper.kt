package com.api.v3.medical_slots.utils

import com.api.v3.doctors.utils.DoctorResponseMapper
import com.api.v3.medical_slots.domain.MedicalSlot
import com.api.v3.medical_slots.dtos.MedicalSlotResponseDto

class MedicalSlotResponseMapper {
    companion object {
        fun map(medicalSlot: MedicalSlot): MedicalSlotResponseDto {
            return MedicalSlotResponseDto(
                DoctorResponseMapper.map(medicalSlot.doctor),
                medicalSlot.availableAt,
                medicalSlot.availableAtZone,
                medicalSlot.canceledAt,
                medicalSlot.canceledAtZone,
                medicalSlot.completedAt,
                medicalSlot.completedAtZone
            )
        }
    }
}