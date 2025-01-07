package com.api.v3.doctors.domain

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId
import java.time.LocalDateTime
import java.time.ZoneId

data class DoctorAuditTrail(
    @BsonId
    val id: ObjectId,
    val doctor: Doctor,
    val createdAt: LocalDateTime,
    val createdAtZone: ZoneId
) {

    companion object {
        fun create(doctor: Doctor): DoctorAuditTrail {
            return DoctorAuditTrail(
                ObjectId(),
                doctor,
                LocalDateTime.now(),
                ZoneId.systemDefault()
            )
        }
    }
}