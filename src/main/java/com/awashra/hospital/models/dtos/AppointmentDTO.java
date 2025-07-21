package com.awashra.hospital.models.dtos;

import com.awashra.hospital.models.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentDTO {
    private Long id;
    private LocalDateTime date;
    private Long patientId;
    private Long doctorId;
    private String reason;
    private Status status;
}

