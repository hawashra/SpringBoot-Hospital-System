package com.awashra.hospital.repositories;

import com.awashra.hospital.models.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    // Additional query methods can be defined here if needed
}
