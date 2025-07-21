package com.awashra.hospital.services;

import com.awashra.hospital.exception.custom.ResourceNotFoundException;
import com.awashra.hospital.mappers.Mapper;
import com.awashra.hospital.models.dtos.AppointmentDTO;
import com.awashra.hospital.models.entities.Appointment;
import com.awashra.hospital.repositories.AppointmentRepository;
import com.awashra.hospital.repositories.DoctorRepository;
import com.awashra.hospital.repositories.PatientRepository;
import jakarta.persistence.criteria.Predicate;
import org.hibernate.ResourceClosedException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final Mapper<Appointment, AppointmentDTO> appointmentMapper;

    public AppointmentService(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository, PatientRepository patientRepository, Mapper<Appointment, AppointmentDTO> appointmentMapper) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.appointmentMapper = appointmentMapper;
    }
    // CREATE
    public void saveAppointment(AppointmentDTO appointmentDTO) {
        appointmentRepository.save(appointmentMapper.reverseMap(appointmentDTO));
    }
    // READ
    public AppointmentDTO getAppointmentById(Long appointmentId) {
        return appointmentRepository.findById(appointmentId)
                .map(appointmentMapper::map)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Appointment not found with id: " + appointmentId));
    }
    // DELETE
    public void deleteAppointmentById(Long appointmentId) {
        if (!appointmentRepository.existsById(appointmentId)) {
            throw new ResourceNotFoundException(
                    "Appointment not found with id: " + appointmentId);
        }
        appointmentRepository.deleteById(appointmentId);
    }
    // UPDATE
    public AppointmentDTO updateAppointment(Long appointmentId, AppointmentDTO appointmentDTO) {
        Appointment existingAppointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new ResourceClosedException(
                        "Appointment not found with id: " + appointmentId));
        existingAppointment.setDate(appointmentDTO.getDate());
        existingAppointment.setDoctor(doctorRepository.findById(appointmentDTO.getDoctorId())
                .orElseThrow(() -> new ResourceClosedException(
                        "Doctor not found with id: " + appointmentDTO.getDoctorId())));
        existingAppointment.setPatient(patientRepository.findById(appointmentDTO.getPatientId())
                .orElseThrow(() -> new ResourceClosedException(
                        "Patient not found with id: " + appointmentDTO.getPatientId())));
        Appointment updatedAppointment = appointmentRepository.save(existingAppointment);
        return appointmentMapper.map(updatedAppointment);
    }
    // FIND APPOINTMENTS BY DOCTOR OR PATIENT (FILTER)
    public List<AppointmentDTO> findAppointments(Long doctorId, Long patientId) {
        List<Appointment> appointments = appointmentRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (doctorId != null) {
                predicates.add(cb.equal(root.get("doctor").get("id"), doctorId));
            }
            if (patientId != null) {
                predicates.add(cb.equal(root.get("patient").get("id"), patientId));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        });

        return appointments.stream()
                .map(appointmentMapper::map)
                .toList();
    }
}
