package com.atakanunlu.SpringDataJPAProjection;

import com.atakanunlu.SpringDataJPAProjection.dto.BloodGroupStats;
import com.atakanunlu.SpringDataJPAProjection.dto.CPatientInfo;
import com.atakanunlu.SpringDataJPAProjection.dto.IPatientInfo;
import com.atakanunlu.SpringDataJPAProjection.entity.Patient;
import com.atakanunlu.SpringDataJPAProjection.repository.PatientRepository;
import com.atakanunlu.SpringDataJPAProjection.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PatientServiceTest {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientService patientService;

    @Test
    public void testPatient(){

//        List<Patient> getAllPatient = patientRepository.findAll();

//        List<IPatientInfo> getAllPatientInfo = patientRepository.getAllPatientInfo();

//        for (Patient patientList: getAllPatient){
//            System.out.println(patientList);
//        }

//        getAllPatient.forEach(System.out::println);

//        getAllPatientInfo.forEach(System.out::println);

//        for(IPatientInfo getAll : getAllPatientInfo){
//            System.out.println("ID: " + getAll.getId() +
//                    " ,Name: " + getAll.getName() +
//                    " ,Email: " + getAll.getEmail());
//        }


//        List<CPatientInfo> getAllPatientInfo = patientRepository.getAllPatientInfoC();
//
//        for (CPatientInfo getAllInfo: getAllPatientInfo){
//            System.out.println(getAllInfo);
//        }

//        List<BloodGroupStats> getBloodGroupStats = patientRepository.getBloodGroupStats();
//
//        for (var bloodGroupStats : getBloodGroupStats){
///            System.out.println(bloodGroupStats);
//        }

//        int guncelle = patientRepository.updatePatientNameWithId("Atakan Ünlü",1L);
//        System.out.println(guncelle);
//
//
//        Patient patient = new Patient(); // TRANSIENT
//        patientRepository.save(patient); // MANAGED(PERSISTENCE) OLDU

        patientService.testPatientTransaction();


        List<Patient> patientList = patientRepository.getAllPatientsWithAppointments();
        patientList.forEach(System.out::println);

    }

}
