package com.api.v3.medical_appointments.dtos

import java.time.LocalDateTime

data class MedicalAppointmentBookingDto(
    val ssn: String,
    val medicalLicenseNumber: String,
    val bookedAt: LocalDateTime
)
