package com.api.v3.medical_slots.utils

import com.api.v3.medical_slots.domain.MedicalSlot
import com.api.v3.medical_slots.domain.MedicalSlotRepository
import com.api.v3.medical_slots.exceptions.NonExistentMedicalSlotException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.bson.types.ObjectId
import org.springframework.stereotype.Component

@Component
class MedicalSlotFinderUtil(
    private val medicalSlotRepository: MedicalSlotRepository
) {

    suspend fun findById(id: String): MedicalSlot {
        return withContext(Dispatchers.IO) {
            val foundMedicalSlot = medicalSlotRepository.findById(ObjectId(id))
            if (foundMedicalSlot == null) {
                throw NonExistentMedicalSlotException(id)
            }
            foundMedicalSlot
        }
    }
}