package com.api.v3.medical_slots.exceptions

import java.time.LocalDateTime

class UnavailableMedicalSlotException(availableAt: LocalDateTime)
    : RuntimeException("The given datetime $availableAt is already associated with an active medical slot.")