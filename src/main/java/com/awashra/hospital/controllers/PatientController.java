package com.awashra.hospital.controllers;

import com.awashra.hospital.models.dtos.PatientDTO;
import com.awashra.hospital.services.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }
    @GetMapping("")
    public ResponseEntity<List<PatientDTO>> getAllPatients() {
        List<PatientDTO> patientsDTOs = patientService.getAllPatients();
        return ResponseEntity.ok(patientsDTOs);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable Long id) {
        PatientDTO patientDTO = patientService.getPatientById(id);
        return ResponseEntity.ok(patientDTO);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deletePatientById(id);
        return ResponseEntity.ok().build();
    }
}
