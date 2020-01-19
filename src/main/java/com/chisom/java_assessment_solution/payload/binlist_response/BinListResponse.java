package com.chisom.java_assessment_solution.payload.binlist_response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BinListResponse {
    private String scheme;
    private String type;
    private String brand;
    private boolean prepaid;
    Country country;
    Bank bank;
    Number number;
}
