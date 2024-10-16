package com.vinilon.pipa.repository;

import com.vinilon.pipa.model.BodyDies;
import com.vinilon.pipa.model.Pin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PinRepository extends JpaRepository<Pin, Integer> {
    @Query(value = "SELECT * FROM pins WHERE :bodyDiesName = ANY(body_dies)", nativeQuery = true)
    List<Pin> findByBodyDiesName(String bodyDiesName);

}
