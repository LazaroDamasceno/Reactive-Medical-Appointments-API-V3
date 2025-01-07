package com.api.v3.doctors.dtos

import com.api.v3.people.dtos.PersonRegistrationDto

data class DoctorHiringDto(
    val licenseNumber: String,
    val personRegistrationDto: PersonRegistrationDto
)
