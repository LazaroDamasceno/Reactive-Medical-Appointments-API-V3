package com.api.v3.people.domain

import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.time.ZoneId

@Document
data class PersonAuditTrail(
    val id: ObjectId,
    val person: Person,
    val createdAt: LocalDateTime,
    val createdAtZone: ZoneId
) {

    companion object {
        fun create(person: Person): PersonAuditTrail {
            return PersonAuditTrail(
                ObjectId(),
                person,
                LocalDateTime.now(),
                ZoneId.systemDefault()
            )
        }
    }
}