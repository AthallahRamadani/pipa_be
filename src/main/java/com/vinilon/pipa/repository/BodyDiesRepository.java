package com.vinilon.pipa.repository;

import com.vinilon.pipa.model.BodyDies;
import com.vinilon.pipa.model.Machine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BodyDiesRepository extends JpaRepository<BodyDies, Integer> {
    @Query("SELECT bd FROM BodyDies bd JOIN bd.machines m WHERE m = :machine")
    List<BodyDies> findByMachine(@Param("machine") Machine machine);
}

