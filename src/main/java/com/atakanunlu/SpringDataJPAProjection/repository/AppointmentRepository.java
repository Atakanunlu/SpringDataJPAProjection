package com.atakanunlu.SpringDataJPAProjection.repository;

import com.atakanunlu.SpringDataJPAProjection.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}