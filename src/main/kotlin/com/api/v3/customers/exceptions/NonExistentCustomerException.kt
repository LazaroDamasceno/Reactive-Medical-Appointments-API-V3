package com.api.v3.customers.exceptions

class NonExistentCustomerException(ssn: String)
    : RuntimeException("Customer whose SSN is $ssn was not found.")