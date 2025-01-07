package com.api.v3.customers.utils

import com.api.v3.customers.domain.Customer
import com.api.v3.customers.domain.CustomerRepository
import com.api.v3.customers.exceptions.NonExistentCustomerException
import com.api.v3.people.domain.PersonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.singleOrNull
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Component

@Component
class CustomerFinderUtil(
    private val customerRepository: CustomerRepository
) {

    suspend fun findBySsn(ssn: String): Customer {
        return withContext(Dispatchers.IO) {
            val foundCustomer = customerRepository
                .findAll()
                .filter { c -> c.person.ssn == ssn }
                .singleOrNull()
            if (foundCustomer == null) {
                throw NonExistentCustomerException(ssn)
            }
            foundCustomer
        }
    }
}