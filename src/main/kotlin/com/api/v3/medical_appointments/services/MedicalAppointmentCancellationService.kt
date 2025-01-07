package com.api.v3.medical_appointments.services

interface MedicalAppointmentCancellationService {
    suspend fun cancel(id: String)
}