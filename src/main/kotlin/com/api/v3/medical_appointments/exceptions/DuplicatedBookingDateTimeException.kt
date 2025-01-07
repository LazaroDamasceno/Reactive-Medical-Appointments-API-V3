package com.api.v3.medical_appointments.exceptions

import java.time.LocalDateTime

class DuplicatedBookingDateTimeException(bookedAt: LocalDateTime)
    : RuntimeException("The given booking datetime $bookedAt is already associated with an active medical appointment.")