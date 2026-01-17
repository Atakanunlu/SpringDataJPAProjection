package com.atakanunlu.SpringDataJPAProjection.repository;

import com.atakanunlu.SpringDataJPAProjection.entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
}