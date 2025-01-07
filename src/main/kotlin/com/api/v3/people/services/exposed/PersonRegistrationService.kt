package com.api.v3.people.services.exposed

import com.api.v3.people.domain.Person
import com.api.v3.people.dtos.PersonRegistrationDto

interface PersonRegistrationService {
    suspend fun register(registrationDto: PersonRegistrationDto): Person
}