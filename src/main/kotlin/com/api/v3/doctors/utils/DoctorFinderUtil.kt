package com.api.v3.doctors.utils

import com.api.v3.doctors.domain.Doctor
import com.api.v3.doctors.domain.DoctorRepository
import com.api.v3.doctors.exceptions.NonExistentDoctorException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.singleOrNull
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Component

@Component
class DoctorFinderUtil(
    private val doctorRepository: DoctorRepository
) {

    suspend fun findByMedicalLicenseNumber(medicalLicenseNumber: String): Doctor {
        return withContext(Dispatchers.IO) {
            val foundDoctor = doctorRepository
                .findAll()
                .filter { d -> d.licenseNumber == medicalLicenseNumber }
                .singleOrNull()
            if (foundDoctor == null) {
                throw NonExistentDoctorException(medicalLicenseNumber)
            }
            foundDoctor
        }
    }
}