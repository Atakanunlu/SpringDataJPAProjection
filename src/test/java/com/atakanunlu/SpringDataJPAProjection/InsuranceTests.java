package com.atakanunlu.SpringDataJPAProjection;

import com.atakanunlu.SpringDataJPAProjection.entity.Appointment;
import com.atakanunlu.SpringDataJPAProjection.entity.Insurance;
import com.atakanunlu.SpringDataJPAProjection.entity.Patient;
import com.atakanunlu.SpringDataJPAProjection.service.AppointmentService;
import com.atakanunlu.SpringDataJPAProjection.service.InsuranceService;
import com.atakanunlu.SpringDataJPAProjection.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
public class InsuranceTests {

    @Autowired
    private PatientService patientService;

    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private AppointmentService appointmentService;

    @Test
    public void testAssignInsuranceToPatient(){

        Insurance insurance = Insurance.builder()
                .provider("AXA SIGORTA")
                .policyNumber("AXA#ATAKAN123")
                .validUntil(LocalDate.of(2027,1,1))
                .build();

        var updatedInsurance = insuranceService.assignInsuranceToPatient(insurance,1L);
        System.out.println(updatedInsurance);


       patientService.deletePatient(1L);

    }

    @Test
    public void testCreateNewAppointment(){

        Appointment appointment = Appointment.builder()
                .appointmentTime(LocalDateTime.of(2026,1,18,11,0,0))
                .reason("fizik tedavi")
                .build();

        var updateAppointment = appointmentService.createNewAppointment(appointment,2L,1L);
        System.out.println(updateAppointment);

        patientService.deletePatient(2L);

    }

}
