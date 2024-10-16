package com.vinilon.pipa.repository;

import com.vinilon.pipa.model.Calibrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalibratorRepository extends JpaRepository<Calibrator, Integer> {
}