package com.api.v3.medical_appointments.services

import com.api.v3.customers.domain.Customer
import com.api.v3.customers.utils.CustomerFinderUtil
import com.api.v3.doctors.domain.Doctor
import com.api.v3.doctors.utils.DoctorFinderUtil
import com.api.v3.medical_appointments.domain.MedicalAppointment
import com.api.v3.medical_appointments.domain.MedicalAppointmentRepository
import com.api.v3.medical_appointments.dtos.MedicalAppointmentBookingDto
import com.api.v3.medical_appointments.dtos.MedicalAppointmentResponseDto
import com.api.v3.medical_appointments.exceptions.DuplicatedBookingDateTimeException
import com.api.v3.medical_appointments.utils.MedicalAppointmentFinderUtil
import com.api.v3.medical_appointments.utils.MedicalAppointmentResponseMapper
import com.api.v3.medical_slots.domain.MedicalSlotRepository
import com.api.v3.medical_slots.exceptions.NonExistentMedicalSlotException
import com.api.v3.medical_slots.utils.MedicalSlotFinderUtil
import jakarta.validation.Valid
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class MedicalAppointmentBookingServiceImpl(
    private val medicalSlotRepository: MedicalSlotRepository,
    private val medicalAppointmentRepository: MedicalAppointmentRepository,
    private val medicalSlotFinderUtil: MedicalSlotFinderUtil,
    private val medicalAppointmentFinderUtil: MedicalAppointmentFinderUtil,
    private val doctorFinderUtil: DoctorFinderUtil,
    private val customerFinderUtil: CustomerFinderUtil
): MedicalAppointmentBookingService {

    override suspend fun book(bookingDto: @Valid MedicalAppointmentBookingDto): MedicalAppointmentResponseDto {
        return withContext(Dispatchers.IO) {
            val foundDoctor = doctorFinderUtil.findByMedicalLicenseNumber(bookingDto.medicalLicenseNumber)
            val foundCustomer = customerFinderUtil.findBySsn(bookingDto.ssn)
            val foundMedicalSlot = medicalSlotFinderUtil.findActiveByDoctorAndAvailableAt(foundDoctor, bookingDto.bookedAt)!!
            onDuplicatedBookingDateTime(foundCustomer, foundDoctor, bookingDto.bookedAt)
            onNonExistentMedicalSlot(foundDoctor, bookingDto.bookedAt)
            val medicalAppointment = MedicalAppointment.create(foundCustomer, foundDoctor, bookingDto.bookedAt)
            foundMedicalSlot.medicalAppointment = medicalAppointment
            medicalSlotRepository.save(foundMedicalSlot)
            val savedMedicalAppointment = medicalAppointmentRepository.save(medicalAppointment)
            MedicalAppointmentResponseMapper.map(savedMedicalAppointment)
        }
    }

    private suspend fun onDuplicatedBookingDateTime(customer: Customer, doctor: Doctor, bookedAt: LocalDateTime) {
        val isGivenBookingDateTimeDuplicated = medicalAppointmentFinderUtil
            .findActiveByCustomerAndDoctorAndBookedAt(customer, doctor, bookedAt) != null
        if (isGivenBookingDateTimeDuplicated) {
            throw DuplicatedBookingDateTimeException(bookedAt)
        }
    }

    private suspend fun onNonExistentMedicalSlot(doctor: Doctor, availableAt: LocalDateTime) {
        val wasMedicalSlotNotFound = medicalSlotFinderUtil
            .findActiveByDoctorAndAvailableAt(doctor, availableAt) == null
        if (wasMedicalSlotNotFound) {
            val message = "Sought medical slot was not found."
            throw NonExistentMedicalSlotException(message)
        }
    }
}