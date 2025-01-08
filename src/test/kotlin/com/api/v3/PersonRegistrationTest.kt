package com.api.v3

import com.api.v3.people.domain.Person
import com.api.v3.people.dtos.PersonRegistrationDto
import com.api.v3.people.services.exposed.PersonRegistrationService
import kotlinx.coroutines.runBlocking
import java.time.LocalDate

import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.mockito.InjectMocks
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.assertEquals

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class PersonRegistrationTest {

	@Autowired
	@InjectMocks
	private lateinit var personRegistrationService: PersonRegistrationService

	private val registrationDto = PersonRegistrationDto(
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
	fun testSuccessful() = runBlocking {
		val actual = personRegistrationService.register(registrationDto)::class
		val expected = Person::class
		assertEquals(expected, actual)
	}

}
