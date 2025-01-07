package com.api.v3.doctors.services

import com.api.v3.doctors.domain.Doctor
import com.api.v3.doctors.domain.DoctorRepository
import com.api.v3.doctors.dtos.DoctorHiringDto
import com.api.v3.doctors.dtos.DoctorResponseDto
import com.api.v3.doctors.exceptions.DuplicatedMedicalLicenseNumberException
import com.api.v3.doctors.utils.DoctorResponseMapper
import com.api.v3.people.services.exposed.PersonRegistrationService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.singleOrNull
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service

@Service
class DoctorHiringServiceImpl(
    private val doctorRepository: DoctorRepository,
    private val personRegistrationService: PersonRegistrationService
): DoctorHiringService {

    override suspend fun hire(hiringDto: DoctorHiringDto): DoctorResponseDto {
        return withContext(Dispatchers.IO) {
            onDuplicatedMedicalLicenseNumber(hiringDto.licenseNumber)
            val savedPerson = personRegistrationService.register(hiringDto.personRegistrationDto)
            val doctor = Doctor.create(savedPerson, hiringDto.licenseNumber)
            val savedDoctor = doctorRepository.save(doctor)
            DoctorResponseMapper.map(savedDoctor)
        }
    }

    private suspend fun onDuplicatedMedicalLicenseNumber(medicalLicenseNumber: String) {
        val isMedicalLicenseNumberDuplicated = doctorRepository
            .findAll()
            .filter { d -> d.licenseNumber == medicalLicenseNumber }
            .singleOrNull() != null
        if (isMedicalLicenseNumberDuplicated) {
            throw DuplicatedMedicalLicenseNumberException()
        }
    }
}