package com.api.v3.medical_slots.services

interface MedicalSlotCancellationService {
    suspend fun cancel(id: String)
}