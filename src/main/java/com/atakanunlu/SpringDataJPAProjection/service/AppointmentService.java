package com.atakanunlu.SpringDataJPAProjection.service;

import com.atakanunlu.SpringDataJPAProjection.entity.Appointment;
import com.atakanunlu.SpringDataJPAProjection.entity.Doctor;
import com.atakanunlu.SpringDataJPAProjection.entity.Patient;
import com.atakanunlu.SpringDataJPAProjection.repository.AppointmentRepository;
import com.atakanunlu.SpringDataJPAProjection.repository.DoctorRepository;
import com.atakanunlu.SpringDataJPAProjection.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;

    @Transactional
    public Appointment createNewAppointment(Appointment appointment, Long patientId, Long doctorId){

        Patient patient = patientRepository.findById(patientId).orElseThrow();
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        appointmentRepository.save(appointment);

        return appointment;

    }

}
