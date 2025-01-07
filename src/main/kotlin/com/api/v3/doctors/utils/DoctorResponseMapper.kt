package com.api.v3.doctors.utils

import com.api.v3.doctors.dtos.DoctorResponseDto
import com.api.v3.doctors.domain.Doctor
import com.api.v3.people.utils.PersonResponseMapper

class DoctorResponseMapper {
    companion object {
        fun map(doctor: Doctor): DoctorResponseDto {
            return DoctorResponseDto(
                doctor.licenseNumber,
                PersonResponseMapper.map(doctor.person)
            )
        }
    }
}