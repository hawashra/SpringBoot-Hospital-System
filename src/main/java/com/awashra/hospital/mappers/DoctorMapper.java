package com.awashra.hospital.mappers;

import com.awashra.hospital.models.dtos.DoctorDTO;
import com.awashra.hospital.models.entities.Doctor;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

@Component
public class DoctorMapper implements Mapper<Doctor, DoctorDTO>{

    private final ModelMapper modelMapper;

    public DoctorMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public DoctorDTO map(Doctor doctor) {
        return modelMapper.map(doctor, DoctorDTO.class);
    }

    @Override
    public Doctor reverseMap(DoctorDTO doctorDTO) {
        return modelMapper.map(doctorDTO, Doctor.class);
    }
}
