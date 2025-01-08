package com.api.v3.medical_appointments.controllers

import com.api.v3.medical_appointments.dtos.MedicalAppointmentBookingDto
import com.api.v3.medical_appointments.dtos.MedicalAppointmentResponseDto
import com.api.v3.medical_appointments.services.MedicalAppointmentBookingService
import com.api.v3.medical_appointments.services.MedicalAppointmentCancellationService
import com.api.v3.medical_appointments.services.MedicalAppointmentCompletionService
import com.api.v3.medical_appointments.services.MedicalAppointmentRetrievalService
import jakarta.validation.Valid
import kotlinx.coroutines.flow.Flow
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v3/medical-appointments")
class MedicalAppointmentController(
    private val bookingService: MedicalAppointmentBookingService,
    private val cancellationService: MedicalAppointmentCancellationService,
    private val completionService: MedicalAppointmentCompletionService,
    private val retrievalService: MedicalAppointmentRetrievalService
) {

    @PostMapping
    suspend fun book(@RequestBody bookingDto: @Valid MedicalAppointmentBookingDto): MedicalAppointmentResponseDto {
        return bookingService.book(bookingDto)
    }

    @PatchMapping("{id}/cancellation")
    suspend fun cancel(@PathVariable id: String) {
        return cancellationService.cancel(id)
    }

    @PatchMapping("{id}/completion")
    suspend fun completed(@PathVariable id: String) {
        return completionService.complete(id)
    }

    @GetMapping("{id}")
    suspend fun findById(@PathVariable id: String): MedicalAppointmentResponseDto {
        return retrievalService.findById(id)
    }

    @GetMapping
    suspend fun findAll(): Flow<MedicalAppointmentResponseDto> {
        return retrievalService.findAll()
    }
}