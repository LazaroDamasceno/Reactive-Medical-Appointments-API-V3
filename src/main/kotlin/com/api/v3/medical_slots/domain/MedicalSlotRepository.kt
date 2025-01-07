package com.api.v3.medical_slots.domain

import org.bson.types.ObjectId
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface MedicalSlotRepository: CoroutineCrudRepository<MedicalSlot, ObjectId>