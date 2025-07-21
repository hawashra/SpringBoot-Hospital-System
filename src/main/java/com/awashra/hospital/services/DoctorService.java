package com.awashra.hospital.services;

import com.awashra.hospital.exception.custom.ResourceNotFoundException;
import com.awashra.hospital.mappers.Mapper;
import com.awashra.hospital.models.dtos.DoctorDTO;
import com.awashra.hospital.models.entities.Doctor;
import com.awashra.hospital.repositories.DoctorRepository;
import org.hibernate.ResourceClosedException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final Mapper<Doctor, DoctorDTO> doctorMapper;
    public DoctorService(DoctorRepository doctorRepository, Mapper<Doctor, DoctorDTO> doctorMapper) {
        this.doctorRepository = doctorRepository;
        this.doctorMapper = doctorMapper;
    }

    // READ all
    public List<DoctorDTO> getAllDoctors() {
        return doctorRepository.findAll().stream().map(doctorMapper::map).toList();
    }
    // READ by ID
    public DoctorDTO getDoctorById(Long id) {
        return doctorRepository.findById(id).map(doctorMapper::map)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));
    }
    // CREATE
    public void saveDoctor(DoctorDTO doctorDTO) {
        doctorRepository.save(doctorMapper.reverseMap(doctorDTO));
    }

    // DELETE by ID
    public void deleteDoctorById(Long doctorId) {
        if (doctorRepository.findById(doctorId).isEmpty()) {
            throw new ResourceNotFoundException("Doctor not found with id: " + doctorId);
        }
        doctorRepository.deleteById(doctorId);
    }
    // UPDATE
    public void updateDoctor(Long doctorId, DoctorDTO doctorDTO) {
        Doctor doctorToUpdate = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceClosedException("Doctor not found with id: " + doctorId));
        doctorToUpdate.setName(doctorDTO.getName());
        doctorToUpdate.setSpecialization(doctorDTO.getSpecialization());
        doctorRepository.save(doctorToUpdate);
    }
}
