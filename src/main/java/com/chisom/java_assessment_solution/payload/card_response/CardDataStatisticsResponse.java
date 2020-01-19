package com.chisom.java_assessment_solution.payload.card_response;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class CardDataStatisticsResponse {
    private boolean success;
    private Integer start;
    private Integer limit;
    private Integer size;
    Map<String , Object> payload = new HashMap<>();
}
