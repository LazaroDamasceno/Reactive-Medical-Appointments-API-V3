package com.api.v3.people.dtos

import java.time.LocalDate

data class PersonModificationDto(
    var firstName: String,
    var middleName: String?,
    var lastName: String,
    var birthDate: LocalDate,
    var gender: String,
    var email: String,
    var phoneNumber: String
)
