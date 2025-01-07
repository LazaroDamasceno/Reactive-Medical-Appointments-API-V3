package com.api.v3.doctors.exceptions

class DuplicatedMedicalLicenseNumberException
    : RuntimeException("The given medical license number is already in use.")