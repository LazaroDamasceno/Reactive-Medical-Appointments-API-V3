package com.api.v3.doctors.dtos

import com.api.v3.people.dtos.PersonResponseDto

data class DoctorResponseDto(
    val licenseNumber: String,
    val personResponseDto: PersonResponseDto
)
