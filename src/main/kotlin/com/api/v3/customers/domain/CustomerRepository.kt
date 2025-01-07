package com.api.v3.customers.domain

import org.bson.types.ObjectId
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface CustomerRepository: CoroutineCrudRepository<Customer, ObjectId>