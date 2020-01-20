package com.chisom.java_assessment_solution.service;

import com.chisom.java_assessment_solution.exception.InvalidBinListCardNumberException;
import com.chisom.java_assessment_solution.model.CardData;
import com.chisom.java_assessment_solution.payload.binlist_response.BinListResponse;
import com.chisom.java_assessment_solution.payload.card_response.CardDataPayload;
import com.chisom.java_assessment_solution.payload.card_response.CardDataResponse;
import com.chisom.java_assessment_solution.payload.card_response.CardDataStatisticsResponse;
import com.chisom.java_assessment_solution.repository.CardDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class CardDataService {

    private static final Logger log = LoggerFactory.getLogger(CardDataService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CardDataRepository cardDataRepository;

    public CardDataResponse verifyCard(String cardNumber) {

        try {
            BinListResponse binListResponse = restTemplate.getForObject("https://lookup.binlist.net/{cardNumber}",
                    BinListResponse.class, cardNumber);

            if (binListResponse != null) {
                saveCardData(cardNumber, binListResponse);
                return TheCardDataResponse(binListResponse);
            }
            return null;

        } catch(Exception ex) {
            throw new InvalidBinListCardNumberException("Card number '" + cardNumber +"' is invalid");
        }


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

    public void saveCardData(String cardNumber, BinListResponse binListResponse) {
        CardData cardData = new CardData();

        cardData.setCardNumber(cardNumber);
        cardData.setScheme(binListResponse.getScheme());
        cardData.setBank(binListResponse.getBank().getName());
        cardData.setPrepaid(binListResponse.isPrepaid());
        cardData.setCountry(binListResponse.getCountry().getName());

        cardDataRepository.save(cardData);
    }

    public CardDataStatisticsResponse getCardStatisticsData(Pageable pageable) {

        CardDataStatisticsResponse cardDataStatisticsResponse = new CardDataStatisticsResponse();
        Page<Map<String, Object>> page = cardDataRepository.getPage(pageable);

        cardDataStatisticsResponse.setSuccess(true);
        cardDataStatisticsResponse.setStart(page.getNumber());
        cardDataStatisticsResponse.setLimit(page.getSize());
        cardDataStatisticsResponse.setSize(cardDataRepository.findAll().size());

        if(page.hasContent()) {
            Map<String, Object> payload = new HashMap<>();
            for(Map<String, Object> result : page) {
                payload.put(String.valueOf(result.get("cardNumber")), Integer.parseInt(String.valueOf(result.get("count"))));
            }
            cardDataStatisticsResponse.setPayload(payload);
        }

        log.info(cardDataStatisticsResponse.toString());

        return cardDataStatisticsResponse;
    }
}
