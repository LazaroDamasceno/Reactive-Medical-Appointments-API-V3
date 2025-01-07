package com.api.v3.customers.dtos

import com.api.v3.common.AddressDto
import com.api.v3.people.dtos.PersonRegistrationDto

data class CustomerRegistrationDto(
    val personRegistrationDto: PersonRegistrationDto,
    val addressDto: AddressDto
)
