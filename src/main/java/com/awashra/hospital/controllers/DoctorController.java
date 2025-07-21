package com.awashra.hospital.controllers;

import com.awashra.hospital.mappers.DoctorMapper;
import com.awashra.hospital.mappers.Mapper;
import com.awashra.hospital.models.dtos.DoctorDTO;
import com.awashra.hospital.models.entities.Doctor;
import com.awashra.hospital.services.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }
    @GetMapping("")
    public ResponseEntity<List<DoctorDTO>> getAllDoctors() {
        List<DoctorDTO> doctorsDTOs = doctorService.getAllDoctors();

        return ResponseEntity.ok(doctorsDTOs);
    }
    @GetMapping("/{id}")
    public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable Long id) {
        DoctorDTO doctorDTO = doctorService.getDoctorById(id); // Let exceptions propagate
        return ResponseEntity.ok(doctorDTO);
    }
    @PostMapping("")
    public ResponseEntity<Void> saveDoctor(@RequestBody DoctorDTO doctorDTO) {
        doctorService.saveDoctor(doctorDTO);
        return ResponseEntity.ok().build();
    }
    // update partially by id
    @PatchMapping("/{id}")
    public ResponseEntity<Void> partialUpdateDoctor(@PathVariable Long id, @RequestBody DoctorDTO doctorDTO) {
        doctorDTO.setId(id);
        doctorService.updateDoctor(id, doctorDTO);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctorById(id);
        return ResponseEntity.ok().build();
    }

}