package com.chisom.java_assessment_solution.payload.card_response;

import lombok.Data;

@Data
public class CardDataResponse {
    private boolean success;
    private CardDataPayload payload;
}
