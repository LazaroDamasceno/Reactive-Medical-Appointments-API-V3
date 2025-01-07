package com.api.v3.doctors.services

interface DoctorTerminationService {
    suspend fun terminate(medicalLicenseNumber: String)
}