package com.api.v3.doctors.services

import com.api.v3.doctors.dtos.DoctorHiringDto
import com.api.v3.doctors.dtos.DoctorResponseDto

interface DoctorHiringService {
    suspend fun hire(hiringDto: DoctorHiringDto): DoctorResponseDto
}