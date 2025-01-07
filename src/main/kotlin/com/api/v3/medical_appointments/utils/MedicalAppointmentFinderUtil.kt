package com.api.v3.medical_appointments.utils

import com.api.v3.medical_appointments.domain.MedicalAppointment
import com.api.v3.medical_appointments.domain.MedicalAppointmentRepository
import com.api.v3.medical_appointments.exceptions.NonExistentMedicalAppointmentException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.bson.types.ObjectId
import org.springframework.stereotype.Component

@Component
class MedicalAppointmentFinderUtil(
    private val medicalAppointmentRepository: MedicalAppointmentRepository
) {

    suspend fun findById(id: String): MedicalAppointment {
        return withContext(Dispatchers.IO) {
            val foundMedicalAppointment = medicalAppointmentRepository.findById(ObjectId(id))
            if (foundMedicalAppointment == null) {
                throw NonExistentMedicalAppointmentException(id)
            }
            foundMedicalAppointment
        }
    }
}