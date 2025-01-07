package com.api.v3.customers.services;

import com.api.v3.customers.dtos.CustomerResponseDto
import kotlinx.coroutines.flow.Flow

interface CustomerRetrievalService {
    suspend fun findBySsn(ssn: String): CustomerResponseDto
    suspend fun findAll(): Flow<CustomerResponseDto>
}
