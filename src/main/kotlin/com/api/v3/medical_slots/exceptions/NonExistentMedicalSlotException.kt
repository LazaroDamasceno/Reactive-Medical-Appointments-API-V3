package com.api.v3.medical_slots.exceptions

import org.bson.types.ObjectId

class NonExistentMedicalSlotException: RuntimeException {

    constructor(id: String): super("Medical slot whose id is $id was not found.")

    constructor(id: ObjectId): super("Medical slot whose id is $id was not found.")
}