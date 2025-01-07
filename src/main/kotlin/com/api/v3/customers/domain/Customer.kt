package com.api.v3.customers.domain

import com.api.v3.common.AddressDto
import com.api.v3.people.domain.Person
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.time.ZoneId

@Document
class Customer(
    var person: Person,
    var address: AddressDto
) {

    @BsonId
    val id: ObjectId = ObjectId()
    val createdAt: LocalDateTime = LocalDateTime.now()
    val createdAtZone: ZoneId = ZoneId.systemDefault()

    companion object {
        fun create(person: Person, address: AddressDto): Customer {
            return Customer(person, address)
        }
    }
}