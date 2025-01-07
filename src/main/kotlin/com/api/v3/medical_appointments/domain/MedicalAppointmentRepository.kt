package com.api.v3.medical_appointments.domain

import org.bson.types.ObjectId
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface MedicalAppointmentRepository: CoroutineCrudRepository<MedicalAppointment, ObjectId>