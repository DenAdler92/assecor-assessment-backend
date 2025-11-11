package de.adler.assecor_assessment.controller;

import de.adler.assecor_assessment.dto.PersonRequestDTO;
import de.adler.assecor_assessment.dto.PersonResponseDTO;
import de.adler.assecor_assessment.service.PersonService;
import org.springframework.http.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PersonService personService;

    private PersonResponseDTO person;

    @BeforeEach
    public void prepareSetup() {
        person = new PersonResponseDTO(1L, "Hans", "Meier", 123456, "Berlin", "blau");
    }

    @Test
    public void getPersonByIdTest() throws Exception {
        when(personService.getPersonById(1L)).thenReturn(person);

        mockMvc.perform(get("/persons/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Hans"))
                .andExpect(jsonPath("$.color").value("blau"));
    }

    @Test
    public void getPersonByIdNullTest() throws Exception {
        when(personService.getPersonById(1L)).thenReturn(null);

        mockMvc.perform(get("/persons/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getAllPersonsTest() throws Exception {
        List<PersonResponseDTO> personResponseDTOList = new ArrayList<>();
        personResponseDTOList.add(person);

        when(personService.getAllPersons()).thenReturn(personResponseDTOList);

        mockMvc.perform(get("/persons"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].name").value("Hans"));
    }

    @Test
    public void getPersonsByColor() throws Exception {
        List<PersonResponseDTO> personResponseDTOList = new ArrayList<>();
        personResponseDTOList.add(person);

        when(personService.getPersonsByColor("blau")).thenReturn(personResponseDTOList);

        mockMvc.perform(get("/persons/color/blau"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].name").value("Hans"))
                .andExpect(jsonPath("$.[0].color").value("blau"));
    }

    @Test
    public void addPerson() throws Exception {
        String personRequestJson = """
                {
                    "lastname": "Müller",
                    "name": "Hans",
                    "zipcode": 12345,
                    "city": "Berlin",
                    "color": 1
                }
                """;

        doNothing().when(personService).addPerson(any(PersonRequestDTO.class));

        mockMvc.perform(post("/persons")
                .contentType(MediaType.APPLICATION_JSON)
                .content(personRequestJson))
                .andExpect(status().isOk())
                .andExpect(content().string("Person added"));


    }


}
