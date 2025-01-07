package com.api.v3.customers.controller

import com.api.v3.customers.dtos.CustomerRegistrationDto
import com.api.v3.customers.dtos.CustomerResponseDto
import com.api.v3.customers.services.CustomerModificationService
import com.api.v3.customers.services.CustomerRegistrationService
import com.api.v3.customers.services.CustomerRetrievalService
import com.api.v3.people.dtos.PersonModificationDto
import jakarta.validation.Valid
import kotlinx.coroutines.flow.Flow
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v3/customers")
class CustomerController(
    private val registrationService: CustomerRegistrationService,
    private val modificationService: CustomerModificationService,
    private val retrievalService: CustomerRetrievalService
) {

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    suspend fun register(@RequestBody registrationDto: @Valid CustomerRegistrationDto): CustomerResponseDto {
        return registrationService.register(registrationDto)
    }

    @PatchMapping("{ssn}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    suspend fun modify(@PathVariable ssn: String, @RequestBody modificationDto: @Valid PersonModificationDto) {
        return modificationService.modify(ssn, modificationDto)
    }

    @GetMapping("{ssn}")
    @ResponseStatus(value = HttpStatus.OK)
    suspend fun findBySsn(@PathVariable ssn: String): CustomerResponseDto {
        return retrievalService.findBySsn(ssn)
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    suspend fun findAll(): Flow<CustomerResponseDto> {
        return retrievalService.findAll()
    }
}