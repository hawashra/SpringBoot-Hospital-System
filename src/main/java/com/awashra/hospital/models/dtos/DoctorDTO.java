package com.awashra.hospital.models.dtos;

import com.awashra.hospital.models.entities.Doctor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorDTO {
    private Long id;
    private String name;
    private String specialization;
}