package com.chisom.java_assessment_solution.repository;

import com.chisom.java_assessment_solution.model.CardData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

public interface CardDataRepository extends JpaRepository<CardData, Long> {

    @Query("SELECT card.cardNumber AS cardNumber, COUNT(card.cardNumber) AS count FROM CardData card GROUP BY card.cardNumber ORDER BY COUNT(card.cardNumber) DESC")
    Page<Map<String, Object>> getPage(Pageable page);
}
