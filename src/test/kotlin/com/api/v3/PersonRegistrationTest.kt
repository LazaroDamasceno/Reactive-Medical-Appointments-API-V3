package com.api.v3

import com.api.v3.people.domain.Person
import com.api.v3.people.dtos.PersonRegistrationDto
import com.api.v3.people.exceptions.DuplicatedEmailException
import com.api.v3.people.exceptions.DuplicatedSsnException
import com.api.v3.people.services.exposed.PersonRegistrationService
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.*
import java.time.LocalDate

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.assertEquals

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class PersonRegistrationTest {

	@Autowired
	private lateinit var personRegistrationService: PersonRegistrationService

	private val registrationDto1 = PersonRegistrationDto(
		"Leo",
		"",
		"Santos",
		LocalDate.parse("2000-12-12"),
		"123456789",
		"male",
		"leosantos@mail.com",
		"1234567890"
	)

	@Test
	@Order(1)
	fun testSuccessful(): Unit = runBlocking {
		val actual = personRegistrationService.register(registrationDto1)::class
		val expected = Person::class
		assertEquals(expected, actual)
	}

    @Test
    @Order(2)
    fun testUnSuccessfulForDuplicatedSsn(): Unit = runBlocking {
        assertThrows<DuplicatedSsnException> {
            personRegistrationService.register(registrationDto1)
        }
    }

    private val registrationDto2 = PersonRegistrationDto(
        "Leo",
        "",
        "Santos",
        LocalDate.parse("2000-12-12"),
        "123456788",
        "male",
        "leosantos@mail.com",
        "1234567890"
    )


    @Test
    @Order(3)
    fun testUnSuccessfulForDuplicatedEmail(): Unit = runBlocking {
        assertThrows<DuplicatedEmailException> {
            personRegistrationService.register(registrationDto2)
        }
    }
}
