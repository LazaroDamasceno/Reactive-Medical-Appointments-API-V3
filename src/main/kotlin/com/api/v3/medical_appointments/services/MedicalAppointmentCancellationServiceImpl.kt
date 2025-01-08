package com.api.v3.medical_appointments.services

import com.api.v3.medical_appointments.domain.MedicalAppointment
import com.api.v3.medical_appointments.domain.MedicalAppointmentRepository
import com.api.v3.medical_appointments.exceptions.ImmutableMedicalAppointmentException
import com.api.v3.medical_appointments.utils.MedicalAppointmentFinderUtil
import com.api.v3.medical_slots.domain.MedicalSlotRepository
import com.api.v3.medical_slots.utils.MedicalSlotFinderUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service

@Service
class MedicalAppointmentCancellationServiceImpl(
    private val medicalAppointmentFinderUtil: MedicalAppointmentFinderUtil,
    private val medicalAppointmentRepository: MedicalAppointmentRepository,
    private val medicalSlotRepository: MedicalSlotRepository,
    private val medicalSlotFinderUtil: MedicalSlotFinderUtil
): MedicalAppointmentCancellationService {

    override suspend fun cancel(id: String) {
        return withContext(Dispatchers.IO) {
            val foundMedicalAppointment = medicalAppointmentFinderUtil.findById(id)
            onCanceledMedicalAppointment(foundMedicalAppointment)
            onCompletedMedicalAppointment(foundMedicalAppointment)
            foundMedicalAppointment.markAsCanceled()
            medicalAppointmentRepository.save(foundMedicalAppointment)
            val foundMedicalSlot = medicalSlotFinderUtil.findActiveByDoctorAndAvailableAt(
                foundMedicalAppointment.doctor,
                foundMedicalAppointment.bookedAt
            )
            foundMedicalSlot!!.medicalAppointment = null
            medicalSlotRepository.save(foundMedicalSlot)
        }
    }

    private suspend fun onCanceledMedicalAppointment(medicalAppointment: MedicalAppointment) {
        val isMedicalAppointmentCanceled = medicalAppointment.canceledAt != null
                && medicalAppointment.completedAt == null
        if (isMedicalAppointmentCanceled) {
            val message = "Medical appointment whose id is ${medicalAppointment.id} is already canceled."
            throw ImmutableMedicalAppointmentException(message)
        }
    }

    private suspend fun onCompletedMedicalAppointment(medicalAppointment: MedicalAppointment) {
        val isMedicalAppointmentCompleted = medicalAppointment.canceledAt == null
                && medicalAppointment.completedAt != null
        if (isMedicalAppointmentCompleted) {
            val message = "Medical appointment whose id is ${medicalAppointment.id} is already canceled."
            throw ImmutableMedicalAppointmentException(message)
        }
    }
}