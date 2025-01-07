package com.api.v3.people.services.interfaces

import com.api.v3.people.domain.Person
import com.api.v3.people.dtos.PersonModificationDto

interface PersonModificationService {
    suspend fun modify(person: Person, modificationDto: PersonModificationDto): Person
}