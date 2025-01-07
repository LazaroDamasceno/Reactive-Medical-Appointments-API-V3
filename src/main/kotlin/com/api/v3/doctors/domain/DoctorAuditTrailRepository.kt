package com.api.v3.doctors.domain

import org.bson.types.ObjectId
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface DoctorAuditTrailRepository: CoroutineCrudRepository<DoctorAuditTrail, ObjectId> {
}