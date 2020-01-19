package com.chisom.java_assessment_solution.repository;

import com.chisom.java_assessment_solution.model.CardData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardDataRepository extends JpaRepository<CardData, Long> {
}
