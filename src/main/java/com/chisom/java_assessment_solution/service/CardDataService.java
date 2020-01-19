package com.chisom.java_assessment_solution.service;

import com.chisom.java_assessment_solution.payload.binlist_response.BinListResponse;
import com.chisom.java_assessment_solution.payload.card_response.CardDataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CardDataService {

    @Autowired
    private RestTemplate restTemplate;

    public CardDataResponse verifyCard(String cardNumber) {

        BinListResponse binListResponse = restTemplate.getForObject("https://lookup.binlist.net/{cardNumber}", BinListResponse.class, cardNumber);
        return TheCardResponse(binListResponse);
    }
}
