package com.api.v3.medical_slots.services

interface MedicalSlotCompletionService {
    suspend fun complete(id: String)
}