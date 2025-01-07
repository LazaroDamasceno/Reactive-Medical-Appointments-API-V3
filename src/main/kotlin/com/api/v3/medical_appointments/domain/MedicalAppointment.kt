package com.api.v3.medical_appointments.domain

import com.api.v3.customers.domain.Customer
import com.api.v3.doctors.domain.Doctor
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.time.ZoneId

@Document
class MedicalAppointment(
    var customer: Customer,
    var doctor: Doctor,
    var bookedAt: LocalDateTime
) {

    @BsonId
    val id: ObjectId = ObjectId()
    val bookedAtZoneId: ZoneId = ZoneId.systemDefault()
    val createdAt: LocalDateTime = LocalDateTime.now()
    val createdAtZone: ZoneId = ZoneId.systemDefault()
    var canceledAt: LocalDateTime? = null
    var canceledAtZone: ZoneId? = null
    var completedAt: LocalDateTime? = null
    var completedAtZone: ZoneId? = null

    companion object {
        fun create(
            customer: Customer,
            doctor: Doctor,
            bookedAt: LocalDateTime
        ): MedicalAppointment {
            return MedicalAppointment(customer, doctor, bookedAt)
        }
    }

    fun markAsCanceled() {
        canceledAt = LocalDateTime.now()
        canceledAtZone = ZoneId.systemDefault()
    }

    fun markAsCompleted() {
        completedAt = LocalDateTime.now()
        completedAtZone = ZoneId.systemDefault()
    }
}