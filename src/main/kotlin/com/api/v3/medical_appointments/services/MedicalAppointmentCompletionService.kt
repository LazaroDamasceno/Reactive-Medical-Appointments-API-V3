package com.api.v3.medical_appointments.services

interface MedicalAppointmentCompletionService {
    suspend fun complete(id: String)
}