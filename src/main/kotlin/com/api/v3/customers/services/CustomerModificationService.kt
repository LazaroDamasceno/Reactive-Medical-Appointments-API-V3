package com.api.v3.customers.services

import com.api.v3.people.dtos.PersonModificationDto

interface CustomerModificationService {
    suspend fun modify(ssn: String, modificationDto: PersonModificationDto)
}