package com.api.v3.medical_appointments.services

import com.api.v3.medical_appointments.dtos.MedicalAppointmentBookingDto
import com.api.v3.medical_appointments.dtos.MedicalAppointmentResponseDto

interface MedicalAppointmentBookingService {
    suspend fun book(bookingDto: MedicalAppointmentBookingDto): MedicalAppointmentResponseDto
}