package com.chisom.java_assessment_solution.service;

import com.chisom.java_assessment_solution.payload.binlist_response.BinListResponse;
import com.chisom.java_assessment_solution.payload.card_response.CardDataPayload;
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
        return TheCardDataResponse(binListResponse);
    }

    public CardDataResponse TheCardDataResponse(BinListResponse binListResponse) {
        CardDataResponse cardDataResponse = new CardDataResponse();
        CardDataPayload cardDataPayload = new CardDataPayload();

        cardDataResponse.setSuccess(binListResponse.getNumber().isLuhn());
        cardDataResponse.setPayload(cardDataPayload);
        cardDataPayload.setScheme(binListResponse.getScheme());
        cardDataPayload.setType(binListResponse.getType());
        cardDataPayload.setBank(binListResponse.getBank().getName());

        return cardDataResponse;
    }
}
