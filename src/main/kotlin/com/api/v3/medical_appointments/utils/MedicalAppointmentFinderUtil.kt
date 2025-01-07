package com.api.v3.medical_appointments.utils

import com.api.v3.customers.domain.Customer
import com.api.v3.doctors.domain.Doctor
import com.api.v3.medical_appointments.domain.MedicalAppointment
import com.api.v3.medical_appointments.domain.MedicalAppointmentRepository
import com.api.v3.medical_appointments.exceptions.NonExistentMedicalAppointmentException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.singleOrNull
import kotlinx.coroutines.withContext
import org.bson.types.ObjectId
import org.springframework.stereotype.Component
import java.time.LocalDateTime

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

    suspend fun findActiveByCustomerAndDoctorAndBookedAt(
        customer: Customer,
        doctor: Doctor,
        bookedAt: LocalDateTime
    ): MedicalAppointment? {
        return withContext(Dispatchers.IO) {
            medicalAppointmentRepository
                .findAll()
                .filter { medicalAppointment ->
                    medicalAppointment.doctor == doctor
                    && medicalAppointment.customer == customer
                    && medicalAppointment.bookedAt == bookedAt
                    && medicalAppointment.canceledAt == null
                    && medicalAppointment.completedAt == null
                }
                .singleOrNull()
        }
    }
}