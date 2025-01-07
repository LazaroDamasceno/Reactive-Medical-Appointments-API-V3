package com.api.v3.customers.services

import com.api.v3.customers.domain.CustomerRepository
import com.api.v3.customers.dtos.CustomerResponseDto
import com.api.v3.customers.utils.CustomerFinderUtil
import com.api.v3.customers.utils.CustomerResponseMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service

@Service
class CustomerRetrievalServiceImpl(
    private val customerFinderUtil: CustomerFinderUtil,
    private val customerRepository: CustomerRepository
): CustomerRetrievalService {

    override suspend fun findBySsn(ssn: String): CustomerResponseDto {
        return withContext(Dispatchers.IO) {
            val foundCustomer = customerFinderUtil.findBySsn(ssn)
            CustomerResponseMapper.map(foundCustomer)
        }
    }

    override suspend fun findAll(): Flow<CustomerResponseDto> {
        return withContext(Dispatchers.IO) {
            customerRepository
                .findAll()
                .map { c -> CustomerResponseMapper.map(c) }
        }
    }
}