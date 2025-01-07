package com.api.v3.medical_slots.services.impl

import com.api.v3.medical_slots.domain.MedicalSlotRepository
import com.api.v3.medical_slots.dtos.MedicalSlotResponseDto
import com.api.v3.medical_slots.services.MedicalSlotRetrievalService
import com.api.v3.medical_slots.utils.MedicalSlotFinderUtil
import com.api.v3.medical_slots.utils.MedicalSlotResponseMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service

@Service
class MedicalSlotRetrievalServiceImpl(
    private val medicalSlotFinderUtil: MedicalSlotFinderUtil,
    private val medicalSlotRepository: MedicalSlotRepository
): MedicalSlotRetrievalService {

    override suspend fun find(id: String): MedicalSlotResponseDto {
        return withContext(Dispatchers.IO) {
            val foundMedicalSlot = medicalSlotFinderUtil.findById(id)
            MedicalSlotResponseMapper.map(foundMedicalSlot)
        }
    }

    override suspend fun findAll(): Flow<MedicalSlotResponseDto> {
        return withContext(Dispatchers.IO) {
            medicalSlotRepository
                .findAll()
                .map { slot -> MedicalSlotResponseMapper.map(slot) }
        }
    }
}