package com.stripe_payment_gateway;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class StripePaymentApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Map<String, Object> createIntentParams;

    @BeforeEach
    void setUp() {
        createIntentParams = new HashMap<>();
        createIntentParams.put("amount", 8000);
        createIntentParams.put("currency", "inr");
    }

    @Test
    void testCreateIntent() throws Exception {
        mockMvc.perform(post("/api/v1/create_intent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createIntentParams)))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.amount").value(8000))
                .andExpect(jsonPath("$.currency").value("inr"))
                .andExpect(jsonPath("$.status").value("requires_capture"))
                .andReturn();
    }

    @Test
    void testCaptureIntent() throws Exception {
        // Step 1: Create Payment Intent
        String createIntentResponse = mockMvc.perform(post("/api/v1/create_intent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createIntentParams)))
                .andExpect(status().isAccepted())
                .andReturn().getResponse().getContentAsString();

        // Extract PaymentIntent ID from response
        Map<String, Object> createIntentResult = objectMapper.readValue(createIntentResponse, Map.class);
        String paymentIntentId = (String) createIntentResult.get("id");

        // Step 2: Capture Payment Intent
        mockMvc.perform(post("/api/v1/capture_intent/" + paymentIntentId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.id").value(paymentIntentId))
                .andExpect(jsonPath("$.status").value("succeeded"))
                .andReturn();
    }

    @Test
    void testCreateRefund() throws Exception {
        // Step 1: Create Payment Intent
        String createIntentResponse = mockMvc.perform(post("/api/v1/create_intent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createIntentParams)))
                .andExpect(status().isAccepted())
                .andReturn().getResponse().getContentAsString();

        // Extract PaymentIntent ID from response
        Map<String, Object> createIntentResult = objectMapper.readValue(createIntentResponse, Map.class);
        String paymentIntentId = (String) createIntentResult.get("id");

        // Step 2: Capture Payment Intent to ensure it's in a capturable state
        mockMvc.perform(post("/api/v1/capture_intent/" + paymentIntentId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());

        // Step 3: Create Refund
        mockMvc.perform(post("/api/v1/create_refund/" + paymentIntentId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.status").value("succeeded"))
                .andExpect(jsonPath("$.payment_intent").value(paymentIntentId))
                .andReturn();
    }

    @Test
    void testGetIntents() throws Exception {
        mockMvc.perform(get("/api/v1/get_intents")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].status").exists())
                .andReturn();
    }
}
