package com.api.v3.medical_slots.services

import com.api.v3.medical_slots.dtos.MedicalSlotResponseDto
import kotlinx.coroutines.flow.Flow

interface MedicalSlotRetrievalService {
    suspend fun find(id: String): MedicalSlotResponseDto
    suspend fun findAll(): Flow<MedicalSlotResponseDto>
}