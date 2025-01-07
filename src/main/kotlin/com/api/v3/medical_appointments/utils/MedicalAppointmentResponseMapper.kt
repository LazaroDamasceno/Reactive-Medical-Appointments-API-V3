package com.api.v3.medical_appointments.utils

import com.api.v3.customers.utils.CustomerResponseMapper
import com.api.v3.doctors.utils.DoctorResponseMapper
import com.api.v3.medical_appointments.domain.MedicalAppointment
import com.api.v3.medical_appointments.dtos.MedicalAppointmentResponseDto

class MedicalAppointmentResponseMapper {
    companion object {
        fun map(medicalAppointment: MedicalAppointment): MedicalAppointmentResponseDto {
            return MedicalAppointmentResponseDto(
                CustomerResponseMapper.map(medicalAppointment.customer),
                DoctorResponseMapper.map(medicalAppointment.doctor),
                medicalAppointment.bookedAt,
                medicalAppointment.bookedAtZone,
                medicalAppointment.canceledAt,
                medicalAppointment.canceledAtZone,
                medicalAppointment.completedAt,
                medicalAppointment.completedAtZone
            )
        }
    }
}