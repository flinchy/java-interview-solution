package com.chisom.java_assessment_solution.payload.binlist_response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bank {

    private String name;
    private String url;
    private String phone;
    private String city;
}
