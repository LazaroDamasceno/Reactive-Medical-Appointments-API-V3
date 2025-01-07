package com.api.v3.customers.services

import com.api.v3.customers.domain.Customer
import com.api.v3.customers.domain.CustomerRepository
import com.api.v3.customers.dtos.CustomerRegistrationDto
import com.api.v3.customers.dtos.CustomerResponseDto
import com.api.v3.customers.utils.CustomerResponseMapper
import com.api.v3.people.exceptions.DuplicatedSsnException
import com.api.v3.people.services.exposed.PersonRegistrationService
import jakarta.validation.Valid
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.singleOrNull
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service

@Service
class CustomerRegistrationServiceImpl(
    private val personRegistrationService: PersonRegistrationService,
    private val customerRepository: CustomerRepository
): CustomerRegistrationService {

    override suspend fun register(registrationDto: @Valid CustomerRegistrationDto): CustomerResponseDto {
        return withContext(Dispatchers.IO) {
            val ssn = registrationDto.personRegistrationDto.ssn
            onGivenDuplicatedSsn(ssn)
            val email = registrationDto.personRegistrationDto.email
            onGivenDuplicatedEmail(email)
            val savedPerson = personRegistrationService.register(registrationDto.personRegistrationDto)
            val customer = Customer.create(savedPerson, registrationDto.addressDto);
            val savedCustomer = customerRepository.save(customer)
            CustomerResponseMapper.map(savedCustomer)
        }
    }

    private suspend fun onGivenDuplicatedSsn(ssn: String) {
        val isGivenSsnDuplicated = customerRepository
            .findAll()
            .filter { c -> c.person.ssn == ssn }
            .singleOrNull() != null
        if (isGivenSsnDuplicated) {
            throw DuplicatedSsnException()
        }
    }

    private suspend fun onGivenDuplicatedEmail(email: String) {
        val isGivenEmailDuplicated = customerRepository
            .findAll()
            .filter { c -> c.person.email == email }
            .singleOrNull()
        if (isGivenEmailDuplicated != null) {
            throw DuplicatedSsnException()
        }
    }
}