package com.api.v3.customers.dtos

import com.api.v3.common.AddressDto
import com.api.v3.people.dtos.PersonResponseDto

data class CustomerResponseDto(
    val personResponseDto: PersonResponseDto,
    val addressDto: AddressDto
)