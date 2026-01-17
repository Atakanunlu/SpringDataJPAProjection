package com.atakanunlu.SpringDataJPAProjection.service;

import com.atakanunlu.SpringDataJPAProjection.entity.Insurance;
import com.atakanunlu.SpringDataJPAProjection.entity.Patient;
import com.atakanunlu.SpringDataJPAProjection.repository.InsuranceRepository;
import com.atakanunlu.SpringDataJPAProjection.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;


    @Transactional
    public Insurance assignInsuranceToPatient(Insurance insurance, Long patientId){

        Patient patient = patientRepository.findById(patientId).orElseThrow();

        patient.setInsurance(insurance); //dirty patient

        insurance.setPatient(patient);

        return insurance;
    }

    @Transactional
    public Insurance updateInsuranceOfAPatient(Insurance insurance, Long patientId){

        Patient patient = patientRepository.findById(patientId).orElseThrow();

        patient.setInsurance(insurance);

        insurance.setPatient(patient);
        return insurance;

    }

    @Transactional
    public Patient removeInsuranceToPatient(Long patientId){

        Patient patient = patientRepository.findById(patientId).orElseThrow();

        patient.setInsurance(null); //dirty patient

        return patient;
    }



}
