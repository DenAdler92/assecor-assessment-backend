package de.adler.assecor_assessment.controller;

import de.adler.assecor_assessment.model.ColorEnum;
import de.adler.assecor_assessment.model.Person;
import de.adler.assecor_assessment.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PersonService personService;

    private Person person;

    @BeforeEach
    public void prepareSetup() {
        person = new Person(1L, "Hans", "Meier",123456, "Berlin", ColorEnum.BLAU);
    }

    @Test
    public void getPersonByIdTest() throws Exception {
        Mockito.when(personService.getPersonById(1L)).thenReturn(person);

        mockMvc.perform(get("/persons/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Hans"))
                .andExpect(jsonPath("$.color").value("BLAU"));
    }


}
