package com.api.v3.people.services

import com.api.v3.people.domain.Person
import com.api.v3.people.domain.PersonRepository
import com.api.v3.people.dtos.PersonRegistrationDto
import com.api.v3.people.services.exposed.PersonRegistrationService
import jakarta.validation.Valid
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service

@Service
class PersonRegistrationServiceImpl(
    private val personRepository: PersonRepository
): PersonRegistrationService {

    override suspend fun register(registrationDto: @Valid PersonRegistrationDto): Person {
        return withContext(Dispatchers.IO) {
            val person = Person.create(registrationDto)
            personRepository.save(person)
        }
    }
}