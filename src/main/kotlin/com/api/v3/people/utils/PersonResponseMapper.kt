package com.api.v3.people.utils

import com.api.v3.people.domain.Person
import com.api.v3.people.dtos.PersonResponseDto

class PersonResponseMapper {
    companion object {
        fun map(person: Person): PersonResponseDto {
            return PersonResponseDto(
                person.fullName(),
                person.birthDate,
                person.ssn,
                person.gender,
                person.email,
                person.phoneNumber
            )
        }
    }
}