package com.api.v3.medical_slots.services

import com.api.v3.medical_appointments.domain.MedicalAppointmentRepository
import com.api.v3.medical_slots.domain.MedicalSlot
import com.api.v3.medical_slots.domain.MedicalSlotRepository
import com.api.v3.medical_slots.exceptions.ImmutableMedicalSlotException
import com.api.v3.medical_slots.utils.MedicalSlotFinderUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service

@Service
class MedicalSlotCancellationServiceImpl(
    private val medicalSlotFinderUtil: MedicalSlotFinderUtil,
    private val medicalSlotRepository: MedicalSlotRepository,
    private val medicalAppointmentRepository: MedicalAppointmentRepository
): MedicalSlotCancellationService {

    override suspend fun cancel(id: String) {
        return withContext(Dispatchers.IO) {
            val foundMedicalSlot = medicalSlotFinderUtil.findById(id)
            onCanceledMedicalSlot(foundMedicalSlot)
            onCompletedMedicalSlot(foundMedicalSlot)
            foundMedicalSlot.markAsCanceled()
            val medicalAppointment = foundMedicalSlot.medicalAppointment
            medicalAppointment!!.markAsCanceled()
            val savedMedicalAppointment = medicalAppointmentRepository.save(medicalAppointment)
            foundMedicalSlot.medicalAppointment = savedMedicalAppointment
            medicalSlotRepository.save(foundMedicalSlot)
        }
    }

    private suspend fun onCanceledMedicalSlot(slot: MedicalSlot) {
        if (slot.canceledAt != null && slot.completedAt == null) {
            val message = "Medical slot whose id id ${slot.id} is already canceled."
            throw ImmutableMedicalSlotException(message)
        }
    }

    private suspend fun onCompletedMedicalSlot(slot: MedicalSlot) {
        if (slot.canceledAt == null && slot.completedAt != null) {
            val message = "Medical slot whose id id ${slot.id} is already completed."
            throw ImmutableMedicalSlotException(message)
        }
    }
}