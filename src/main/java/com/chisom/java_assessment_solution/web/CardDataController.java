package com.chisom.java_assessment_solution.web;

import com.chisom.java_assessment_solution.exception.InvalidPageException;
import com.chisom.java_assessment_solution.exception.InvalidPageResponse;
import com.chisom.java_assessment_solution.payload.card_response.CardDataResponse;
import com.chisom.java_assessment_solution.payload.card_response.CardDataStatisticsResponse;
import com.chisom.java_assessment_solution.service.CardDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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

    @GetMapping(value = "/stats", params = {"start", "limit"})
    public ResponseEntity<CardDataStatisticsResponse> getCardDataStatics(
            @RequestParam("start") Integer start, @RequestParam("limit") Integer limit) {

        try {
            if(start >= 1) start--;
            return new ResponseEntity<>(cardDataService.getCardStatisticsData(PageRequest.of(start, limit)), HttpStatus.OK);

        } catch(Exception ex) {
            throw new InvalidPageException("Invalid page, start must not be less than 1, limit " +
                    "must not be empty, and must be a positive number");
        }
//        if(start >= 1) start--;
//        if(start < 0) throw new InvalidPageException("Invalid Page");

    }
}
