package com.atakanunlu.SpringDataJPAProjection.repository;

import com.atakanunlu.SpringDataJPAProjection.dto.BloodGroupStats;
import com.atakanunlu.SpringDataJPAProjection.dto.CPatientInfo;
import com.atakanunlu.SpringDataJPAProjection.dto.IPatientInfo;
import com.atakanunlu.SpringDataJPAProjection.entity.Patient;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {

    @Query("SELECT p.id AS id, p.name AS name, p.email AS email FROM Patient p")
    List<IPatientInfo> getAllPatientInfo();

    @Query("SELECT new com.atakanunlu.SpringDataJPAProjection.dto.CPatientInfo(p.id, p.name) FROM Patient p")
    List<CPatientInfo> getAllPatientInfoC();

    @Query("select new com.atakanunlu.SpringDataJPAProjection.dto.BloodGroupStats(p.bloodGroup, " +
        "COUNT(p)) from Patient p group by p.bloodGroup order by COUNT(p) DESC")
    List<BloodGroupStats> getBloodGroupStats();

    @Transactional
    @Modifying
    @Query("UPDATE Patient p set p.name = :name where p.id = :id")
    int updatePatientNameWithId(@Param("name") String name, @Param("id") Long id);

}
