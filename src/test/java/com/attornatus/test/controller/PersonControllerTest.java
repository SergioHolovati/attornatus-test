package com.attornatus.test.controller;

import com.attornatus.test.domain.service.person.PersonService;
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
public class PersonControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void ShouldCreateAPerson() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/rest/v1/persons/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Sergio Holovati\",\"birthday\":\"1995-09-19\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void ShouldTryCreateAPersonAndThrowException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/rest/v1/persons/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"\",\"birthday\": \"1995-09-19\"}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void ShouldEditPerson() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/rest/v1/persons/edit/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Sergio Holovati\",\"birthday\": \"1995-09-19\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void ShouldTryEditPersonAndThrowAnException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/rest/v1/persons/edit/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void ShouldCreatePersonWithAddress() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/rest/v1/persons/create/address")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"name\":\"Sergio Holovati\",\n" +
                                "    \"birthday\": \"1995-09-19\",\n" +
                                "    \"addressName\":\"Casa\",\n" +
                                "    \"street\":\"Rua antônio Martiliano de Campos\",\n" +
                                "    \"district\":\"Jardim Alice\",\n" +
                                "    \"postalCode\":\"13346-250\",\n" +
                                "    \"number\":\"440\",\n" +
                                "    \"city\":\"Indaiatuba\",\n" +
                                "    \"isPrincipal\":true\n" +
                                "}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void ShouldCreatePersonWithAddressAndThrowException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/rest/v1/persons/create/address")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"birthday\": \"1995-09-19\",\n" +
                                "    \"addressName\":\"Casa\",\n" +
                                "    \"street\":\"Rua antônio Martiliano de Campos\",\n" +
                                "    \"district\":\"Jardim Alice\",\n" +
                                "    \"postalCode\":\"13346-250\",\n" +
                                "    \"number\":\"440\",\n" +
                                "    \"city\":\"Indaiatuba\",\n" +
                                "    \"isPrincipal\":true\n" +
                                "}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void ShouldAListOfPersonWithAdresses() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/rest/v1/persons")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void ShouldAPersonWithAdresses() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/rest/v1/persons/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
