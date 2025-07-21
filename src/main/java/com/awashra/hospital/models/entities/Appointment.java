package com.awashra.hospital.models.entities;

import com.awashra.hospital.models.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Patient patient;
    @ManyToOne
    private Doctor doctor;
    private LocalDateTime date;
    private String reason;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;
}
