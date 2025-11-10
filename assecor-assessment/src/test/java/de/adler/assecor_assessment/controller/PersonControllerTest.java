package de.adler.assecor_assessment.controller;

import de.adler.assecor_assessment.dto.PersonResponseDTO;
import de.adler.assecor_assessment.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        Mockito.when(personService.getPersonById(1L)).thenReturn(person);

        mockMvc.perform(get("/persons/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Hans"))
                .andExpect(jsonPath("$.color").value("blau"));
    }


}
