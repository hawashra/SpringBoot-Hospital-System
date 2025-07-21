package com.awashra.hospital.mappers;

import com.awashra.hospital.models.dtos.PatientDTO;
import com.awashra.hospital.models.entities.Patient;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PatientMapper implements Mapper<Patient, PatientDTO>{

    private final ModelMapper modelMapper;

    public PatientMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    @Override
    public PatientDTO map(Patient patient) {
        return modelMapper.map(patient, PatientDTO.class);
    }
    @Override
    public Patient reverseMap(PatientDTO patientDTO) {
        return modelMapper.map(patientDTO, Patient.class);
    }
}
