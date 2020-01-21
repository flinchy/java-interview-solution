package com.chisom.java_assessment_solution;

import com.chisom.java_assessment_solution.payload.card_response.CardDataPayload;
import com.chisom.java_assessment_solution.payload.card_response.CardDataResponse;
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

    @Test
    public void getCardData() throws Exception {

        mockMvc.perform(get("/card-scheme/verify/4231233"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{}"));

        verify(cardDataService, times(1)).verifyCard("4231233");

    }

}
