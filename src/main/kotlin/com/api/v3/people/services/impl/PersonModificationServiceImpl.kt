package com.api.v3.people.services.impl

import com.api.v3.people.domain.Person
import com.api.v3.people.domain.PersonAuditTrail
import com.api.v3.people.domain.PersonAuditTrailRepository
import com.api.v3.people.domain.PersonRepository
import com.api.v3.people.dtos.PersonModificationDto
import com.api.v3.people.services.interfaces.PersonModificationService
import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service

@Service
class PersonModificationServiceImpl(
    private val personRepository: PersonRepository,
    private val personAuditTrailRepository: PersonAuditTrailRepository
): PersonModificationService {

    override suspend fun modify(
        person: @NotNull Person,
        modificationDto: @Valid PersonModificationDto
    ): Person {
        return withContext(Dispatchers.IO) {
            val personAuditTrail = PersonAuditTrail.create(person)
            personAuditTrailRepository.save(personAuditTrail)
            person.modify(modificationDto)
            personRepository.save(person)
        }
    }
}