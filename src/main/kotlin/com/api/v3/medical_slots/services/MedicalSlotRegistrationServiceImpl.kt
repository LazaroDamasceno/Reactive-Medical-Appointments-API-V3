package com.api.v3.medical_slots.services

import com.api.v3.doctors.domain.Doctor
import com.api.v3.doctors.utils.DoctorFinderUtil
import com.api.v3.medical_slots.domain.MedicalSlot
import com.api.v3.medical_slots.domain.MedicalSlotRepository
import com.api.v3.medical_slots.dtos.MedicalSlotRegistrationDto
import com.api.v3.medical_slots.dtos.MedicalSlotResponseDto
import com.api.v3.medical_slots.exceptions.UnavailableMedicalSlotException
import com.api.v3.medical_slots.utils.MedicalSlotResponseMapper
import jakarta.validation.Valid
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.singleOrNull
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class MedicalSlotRegistrationServiceImpl(
    private val medicalSlotRepository: MedicalSlotRepository,
    private val doctorFinderUtil: DoctorFinderUtil
): MedicalSlotRegistrationService {

    override suspend fun register(registrationDto: @Valid MedicalSlotRegistrationDto): MedicalSlotResponseDto {
        return withContext(Dispatchers.IO) {
            val foundDoctor = doctorFinderUtil.findByMedicalLicenseNumber(registrationDto.medicalLicenseNumber)
            onExistentMedicalSlot(foundDoctor, registrationDto.availableAt)
            val medicalSlot = MedicalSlot.create(foundDoctor, registrationDto.availableAt)
            val savedMedicalSlot = medicalSlotRepository.save(medicalSlot)
            MedicalSlotResponseMapper.map(savedMedicalSlot)
        }
    }

    private suspend fun onExistentMedicalSlot(doctor: Doctor, availableAt: LocalDateTime) {
        val isGivenAvailableAtAlreadyUnavailable = medicalSlotRepository
            .findAll()
            .filter { slot ->
                slot.doctor.id == doctor.id
                && slot.availableAt == availableAt
            }
            .singleOrNull() != null
        if (isGivenAvailableAtAlreadyUnavailable) {
            throw UnavailableMedicalSlotException(availableAt)
        }
    }
}