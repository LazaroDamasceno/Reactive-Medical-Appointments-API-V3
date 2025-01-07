package com.api.v3.medical_slots.utils

import com.api.v3.doctors.domain.Doctor
import com.api.v3.medical_slots.domain.MedicalSlot
import com.api.v3.medical_slots.domain.MedicalSlotRepository
import com.api.v3.medical_slots.exceptions.NonExistentMedicalSlotException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.singleOrNull
import kotlinx.coroutines.withContext
import org.bson.types.ObjectId
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class MedicalSlotFinderUtil(
    private val medicalSlotRepository: MedicalSlotRepository
) {

    suspend fun findById(id: String): MedicalSlot {
        return withContext(Dispatchers.IO) {
            val foundMedicalSlot = medicalSlotRepository.findById(ObjectId(id))
            if (foundMedicalSlot == null) {
                val message = "Medical slot whose id is $id was not found."
                throw NonExistentMedicalSlotException(message)
            }
            foundMedicalSlot
        }
    }

    suspend fun findActiveByDoctorAndAvailableAt(doctor: Doctor, availableAt: LocalDateTime): MedicalSlot? {
        return withContext(Dispatchers.IO) {
            medicalSlotRepository
                .findAll()
                .filter { slot ->
                    slot.doctor == doctor
                    && slot.availableAt == availableAt
                    && slot.canceledAt == null
                    && slot.completedAt == null
                }
                .singleOrNull()
        }
    }
}