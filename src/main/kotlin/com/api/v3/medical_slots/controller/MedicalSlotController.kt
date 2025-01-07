package com.api.v3.medical_slots.controller

import com.api.v3.medical_slots.dtos.MedicalSlotRegistrationDto
import com.api.v3.medical_slots.dtos.MedicalSlotResponseDto
import com.api.v3.medical_slots.services.MedicalSlotCancellationService
import com.api.v3.medical_slots.services.MedicalSlotCompletionService
import com.api.v3.medical_slots.services.MedicalSlotRegistrationService
import com.api.v3.medical_slots.services.MedicalSlotRetrievalService
import jakarta.validation.Valid
import kotlinx.coroutines.flow.Flow
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v3/medical-slots")
class MedicalSlotController(
    private val registrationService: MedicalSlotRegistrationService,
    private val retrievalService: MedicalSlotRetrievalService,
    private val completionService: MedicalSlotCompletionService,
    private val cancellationService: MedicalSlotCancellationService
) {

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    suspend fun register(
        @RequestBody registrationDto: @Valid MedicalSlotRegistrationDto
    ): MedicalSlotResponseDto {
        return registrationService.register(registrationDto)
    }

    @GetMapping("{id}")
    @ResponseStatus(value = HttpStatus.OK)
    suspend fun find(@PathVariable id: String): MedicalSlotResponseDto {
        return retrievalService.find(id)
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    suspend fun findAll(): Flow<MedicalSlotResponseDto> {
        return retrievalService.findAll()
    }

    @PatchMapping("{id}/completion")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    suspend fun complete(@PathVariable id: String) {
        return completionService.complete(id)
    }

    @PatchMapping("{id}/cancellation")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    suspend fun cancel(@PathVariable id: String) {
        return cancellationService.cancel(id)
    }
}