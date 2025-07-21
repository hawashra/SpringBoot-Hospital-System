package com.awashra.hospital.services;

import com.awashra.hospital.exception.custom.ResourceNotFoundException;
import com.awashra.hospital.mappers.Mapper;
import com.awashra.hospital.models.dtos.PatientDTO;
import com.awashra.hospital.models.entities.Patient;
import com.awashra.hospital.repositories.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    private final Mapper<Patient, PatientDTO> patientMapper;

    public PatientService(PatientRepository patientRepository, Mapper<Patient, PatientDTO> patientMapper) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
    }
    // CREATE or UPDATE (save)
    public void savePatient(PatientDTO patient) {
        patientRepository.save(patientMapper.reverseMap(patient));
    }
    // READ by ID
    public PatientDTO getPatientById(Long patientId) {
        return patientRepository.findById(patientId).map(patientMapper::map)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + patientId));
    }
    // DELETE by ID
    public void deletePatientById(Long patientId) {
        if (patientRepository.findById(patientId).isEmpty()) {
            throw new ResourceNotFoundException("Patient not found with id: " + patientId);
        }
        patientRepository.deleteById(patientId);
    }
    // READ all
    public List<PatientDTO> getAllPatients() {
        return patientRepository.findAll().stream().map(patientMapper::map).toList();
    }
}
