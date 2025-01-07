package com.api.v3.doctors.domain

import com.api.v3.people.domain.Person
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.time.ZoneId

@Document
class Doctor(
    var person: Person,
    var licenseNumber: String
) {

    @BsonId
    var id: ObjectId = ObjectId()
    val createdAt: LocalDateTime = LocalDateTime.now()
    val createdAtZone: ZoneId = ZoneId.systemDefault()
    var hiredAt: LocalDateTime = LocalDateTime.now()
    var hiredAtZone: ZoneId = ZoneId.systemDefault()
    var terminatedAt: LocalDateTime? = null
    var terminatedAtZone: ZoneId? = null

    companion object {
        fun create(person: Person, licenseNumber: String): Doctor {
            return Doctor(person, licenseNumber)
        }
    }

    fun markAsTerminated() {
        terminatedAt = LocalDateTime.now()
        terminatedAtZone = ZoneId.systemDefault()
    }

    fun markAsRehired() {
        terminatedAt = null
        terminatedAtZone = null
    }
}