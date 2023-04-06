package com.attornatus.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void ShouldReturnAListOfAdressesByPersonId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/rest/v1/address/person/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void ShouldCreateAnAddress() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/rest/v1/address/create")
                        .contentType(MediaType.APPLICATION_JSON).content("{\n" +
                                "    \"addressName\":\"Trabalho\",\n" +
                                "    \"street\":\"Rua Soldado joão carlos de oliveira\",\n" +
                                "    \"district\":\"Jardins\",\n" +
                                "    \"postalCode\":\"13346-356\",\n" +
                                "    \"number\":\"251\",\n" +
                                "    \"city\":\"São Paulo\",\n" +
                                "    \"isPrincipal\":true,\n" +
                                "    \"personId\":\"2\"\n" +
                                "}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void ShouldEditAnAddress() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/rest/v1/address/edit/1")
                        .contentType(MediaType.APPLICATION_JSON).content("{\n" +
                                "    \"addressName\":\"Casa\",\n" +
                                "    \"street\":\"Rua Soldado joão carlos de oliveira\",\n" +
                                "    \"district\":\"Jardins\",\n" +
                                "    \"postalCode\":\"13346-356\",\n" +
                                "    \"number\":\"251\",\n" +
                                "    \"city\":\"São Paulo\",\n" +
                                "    \"isPrincipal\":true,\n" +
                                "    \"personId\":\"2\"\n" +
                                "}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


}
