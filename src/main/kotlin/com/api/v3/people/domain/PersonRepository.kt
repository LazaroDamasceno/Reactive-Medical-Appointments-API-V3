package com.api.v3.people.domain

import org.bson.types.ObjectId
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface PersonRepository: CoroutineCrudRepository<Person, ObjectId>