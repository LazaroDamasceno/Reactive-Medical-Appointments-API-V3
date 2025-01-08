package com.api.v3.medical_slots.domain

import com.api.v3.doctors.domain.Doctor
import com.api.v3.medical_appointments.domain.MedicalAppointment
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.time.ZoneId

@Document
class MedicalSlot(
    val doctor: Doctor,
    val availableAt: LocalDateTime
) {

    @BsonId
    val id: ObjectId = ObjectId()
    val availableAtZone: ZoneId = ZoneId.systemDefault()
    var medicalAppointment: MedicalAppointment? = null
    val createdAt: LocalDateTime = LocalDateTime.now()
    val createdAtZone: ZoneId = ZoneId.systemDefault()
    var canceledAt: LocalDateTime? = null
    var canceledAtZone: ZoneId? = null
    var completedAt: LocalDateTime? = null
    var completedAtZone: ZoneId? = null

    companion object {
        fun create(doctor: Doctor, availableAt: LocalDateTime): MedicalSlot {
            return MedicalSlot(doctor, availableAt)
        }
    }

    fun markAsCompleted() {
        completedAt = LocalDateTime.now()
        completedAtZone = ZoneId.systemDefault()
    }

    fun markAsCanceled() {
        canceledAt = LocalDateTime.now()
        canceledAtZone = ZoneId.systemDefault()
    }
}