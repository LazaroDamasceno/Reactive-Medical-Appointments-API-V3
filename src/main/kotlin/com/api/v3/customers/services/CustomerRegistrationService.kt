package com.api.v3.customers.services

import com.api.v3.customers.dtos.CustomerRegistrationDto
import com.api.v3.customers.dtos.CustomerResponseDto

interface CustomerRegistrationService {
    suspend fun register(registrationDto: CustomerRegistrationDto): CustomerResponseDto
}