package com.chisom.java_assessment_solution;

import com.chisom.java_assessment_solution.payload.card_response.CardDataPayload;
import com.chisom.java_assessment_solution.payload.card_response.CardDataResponse;
import com.chisom.java_assessment_solution.payload.card_response.CardDataStatisticsResponse;
import com.chisom.java_assessment_solution.service.CardDataService;
import com.chisom.java_assessment_solution.web.CardDataController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class CardDataControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CardDataService cardDataService;

    @Autowired
    CardDataController cardDataController;

    @Before
    public void setup() {
        CardDataResponse cardDataResponse = new CardDataResponse();
        CardDataPayload cardDataPayload = new CardDataPayload();
        cardDataResponse.setPayload(cardDataPayload);
        cardDataPayload.setScheme("vise");
        cardDataPayload.setType("credit");
        cardDataPayload.setBank("FIA CARD SERVICES, N.A.");

        given(cardDataService.verifyCard(any())).willReturn(cardDataResponse);
    }

    @Before
    public void setupA() {
        CardDataStatisticsResponse cardDataStatisticsResponse = new CardDataStatisticsResponse();
        Map<String, Object> payload = new HashMap<>();

        payload.put("432018", 1);
        payload.put("457173", 3);
        cardDataStatisticsResponse.setPayload(payload);
        cardDataStatisticsResponse.setStart(1);
        cardDataStatisticsResponse.setLimit(2);
        cardDataStatisticsResponse.setSize(5);
        cardDataStatisticsResponse.setSuccess(true);

        given(cardDataService.getCardStatisticsData(any())).willReturn(cardDataStatisticsResponse);
    }

    @Test
    public void getCardData() throws Exception {

        mockMvc.perform(get("/card-scheme/verify/4231233"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{}"));

        verify(cardDataService, times(1)).verifyCard("4231233");
    }

    @Test
    public void getCardDataStatics() throws Exception {

        mockMvc.perform(get("/card-scheme/stats?start=1&limit=2"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }

}
