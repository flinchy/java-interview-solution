package com.chisom.java_assessment_solution.payload.card_response;

import lombok.Data;

@Data
public class CardDataPayload {
    private String scheme;
    private String type;
    private String bank;
}
