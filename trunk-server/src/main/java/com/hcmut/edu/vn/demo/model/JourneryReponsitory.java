package com.hcmut.edu.vn.demo.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JourneryReponsitory extends JpaRepository<JourneryModel, Integer> {
    List<JourneryModel> findAllByJourneyCode(String journeyCode);
}
