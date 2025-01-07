package com.api.v3.doctors.services.impl

import com.api.v3.doctors.domain.DoctorAuditTrail
import com.api.v3.doctors.domain.DoctorAuditTrailRepository
import com.api.v3.doctors.domain.DoctorRepository
import com.api.v3.doctors.exceptions.ImmutableDoctorException
import com.api.v3.doctors.services.DoctorTerminationService
import com.api.v3.doctors.utils.DoctorFinderUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service

@Service
class DoctorTerminationServiceImpl(
    private val doctorFinderUtil: DoctorFinderUtil,
    private val doctorRepository: DoctorRepository,
    private val doctorAuditTrailRepository: DoctorAuditTrailRepository
): DoctorTerminationService {

    override suspend fun terminate(medicalLicenseNumber: String) {
        return withContext(Dispatchers.IO) {
            val foundDoctor = doctorFinderUtil.findByMedicalLicenseNumber(medicalLicenseNumber)
            if (foundDoctor.terminatedAt != null) {
                val message = "Doctor whose license number is $medicalLicenseNumber is already terminated."
                throw ImmutableDoctorException(message)
            }
            val auditTrail = DoctorAuditTrail.create(foundDoctor)
            doctorAuditTrailRepository.save(auditTrail)
            foundDoctor.markAsTerminated()
            doctorRepository.save(foundDoctor)
        }
    }
}