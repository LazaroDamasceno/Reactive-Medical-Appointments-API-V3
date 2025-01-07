package com.api.v3.customers.services

import com.api.v3.customers.domain.CustomerRepository
import com.api.v3.customers.utils.CustomerFinderUtil
import com.api.v3.people.dtos.PersonModificationDto
import com.api.v3.people.services.exposed.PersonModificationService
import jakarta.validation.Valid
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service

@Service
class CustomerModificationServiceImpl(
    private val customerFinderUtil: CustomerFinderUtil,
    private val personModificationService: PersonModificationService,
    private val customerRepository: CustomerRepository
): CustomerModificationService {

    override suspend fun modify(ssn: String, modificationDto: @Valid PersonModificationDto) {
        return withContext(Dispatchers.IO) {
            val foundCustomer = customerFinderUtil.findBySsn(ssn)
            val modifiedPerson = personModificationService.modify(foundCustomer.person, modificationDto)
            foundCustomer.person = modifiedPerson
            customerRepository.save(foundCustomer)
        }
    }
}