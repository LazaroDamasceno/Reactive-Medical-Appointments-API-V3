package com.api.v3.doctors.exceptions

class NonExistentDoctorException(medicalLicenseNumber: String)
    : RuntimeException("Doctor whose license number is $medicalLicenseNumber was not found.")