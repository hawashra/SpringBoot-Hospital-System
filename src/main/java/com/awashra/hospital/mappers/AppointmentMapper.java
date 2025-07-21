package com.awashra.hospital.mappers;

import com.awashra.hospital.models.dtos.AppointmentDTO;
import com.awashra.hospital.models.entities.Appointment;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper implements Mapper<Appointment, AppointmentDTO>{
    private final ModelMapper modelMapper;

    public AppointmentMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    @Override
    public AppointmentDTO map(Appointment appointment) {
        return modelMapper.map(appointment, AppointmentDTO.class);
    }
    @Override
    public Appointment reverseMap(AppointmentDTO appointmentDTO) {
        return modelMapper.map(appointmentDTO, Appointment.class);
    }
}
