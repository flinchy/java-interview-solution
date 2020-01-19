package com.chisom.java_assessment_solution.web;

import com.chisom.java_assessment_solution.payload.card_response.CardDataResponse;
import com.chisom.java_assessment_solution.payload.card_response.CardDataStatisticsResponse;
import com.chisom.java_assessment_solution.service.CardDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card-scheme")
@CrossOrigin
public class CardDataController {

    @Autowired
    private CardDataService cardDataService;

    @GetMapping("/verify/{cardNumber}")
    public ResponseEntity<CardDataResponse> getCardData(@PathVariable String cardNumber) {
        CardDataResponse cardDataResponse = cardDataService.verifyCard(cardNumber);

        return new ResponseEntity<>(cardDataResponse, HttpStatus.OK);
    }

}
