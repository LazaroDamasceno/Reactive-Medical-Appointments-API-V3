package com.api.v3.people.domain

import com.api.v3.people.dtos.PersonModificationDto
import com.api.v3.people.dtos.PersonRegistrationDto
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId

@Document
class Person(
    var firstName: String,
    var middleName: String?,
    var lastName: String,
    var birthDate: LocalDate,
    var ssn: String,
    var gender: String,
    var email: String,
    var phoneNumber: String
) {

    @BsonId
    val id: ObjectId = ObjectId()
    val createdAt: LocalDateTime = LocalDateTime.now()
    val createdAtZone: ZoneId = ZoneId.systemDefault()
    lateinit var modifiedAt: LocalDateTime
    lateinit var modifiedAtZone: ZoneId

    fun fullName(): String {
        if (middleName.isNullOrEmpty()) {
            return "$firstName $lastName"
        }
        return "$firstName $middleName $lastName"
    }

    companion object {
        fun create(registrationDto: PersonRegistrationDto): Person {
            return Person(
                registrationDto.firstName,
                registrationDto.middleName,
                registrationDto.lastName,
                registrationDto.birthDate,
                registrationDto.ssn,
                registrationDto.gender,
                registrationDto.email,
                registrationDto.phoneNumber
            )
        }
    }

    fun modify(modificationDto: PersonModificationDto) {
        firstName = modificationDto.firstName
        middleName = modificationDto.middleName
        lastName = modificationDto.lastName
        birthDate = modificationDto.birthDate
        gender = modificationDto.gender
        email = modificationDto.email
        phoneNumber = modificationDto.phoneNumber
        modifiedAt = LocalDateTime.now()
        modifiedAtZone = ZoneId.systemDefault()
    }
}