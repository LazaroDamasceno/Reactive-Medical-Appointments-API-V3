package com.api.v3.doctors.controllers

import com.api.v3.doctors.dtos.DoctorHiringDto
import com.api.v3.doctors.dtos.DoctorResponseDto
import com.api.v3.doctors.services.DoctorHiringService
import com.api.v3.doctors.services.DoctorRehiringService
import com.api.v3.doctors.services.DoctorRetrievalService
import com.api.v3.doctors.services.DoctorTerminationService
import jakarta.validation.Valid
import kotlinx.coroutines.flow.Flow
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v3/doctors")
class DoctorController(
    private val hiringService: DoctorHiringService,
    private val rehiringService: DoctorRehiringService,
    private val terminationService: DoctorTerminationService,
    private val retrievalService: DoctorRetrievalService
) {

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    suspend fun hire(@RequestBody hiringDto: @Valid DoctorHiringDto): DoctorResponseDto {
        return hiringService.hire(hiringDto)
    }

    @PatchMapping("{medicalLicenseNumber}/rehiring")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    suspend fun rehire(@PathVariable medicalLicenseNumber: String) {
        return rehiringService.rehire(medicalLicenseNumber)
    }

    @PatchMapping("{medicalLicenseNumber}/termination")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    suspend fun terminate(@PathVariable medicalLicenseNumber: String) {
        return terminationService.terminate(medicalLicenseNumber)
    }

    @GetMapping("{medicalLicenseNumber}")
    @ResponseStatus(value = HttpStatus.OK)
    suspend fun findByMedicalLicenseNumber(@PathVariable medicalLicenseNumber: String): DoctorResponseDto {
        return retrievalService.findByMedicalLicenseNumber(medicalLicenseNumber)
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    suspend fun findAll(): Flow<DoctorResponseDto> {
        return retrievalService.findAll()
    }
}