package com.api.v3.people.exceptions

class DuplicatedEmailException: RuntimeException("The given email is already in use.")