package com.api.v3.doctors.services

interface DoctorRehiringService {
    suspend fun rehire(medicalLicenseNumber: String)
}