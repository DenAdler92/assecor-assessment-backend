package de.adler.assecor_assessment.service;

import de.adler.assecor_assessment.dto.PersonRequestDTO;
import de.adler.assecor_assessment.dto.PersonResponseDTO;
import de.adler.assecor_assessment.mapper.PersonMapper;
import de.adler.assecor_assessment.model.ColorEnum;
import de.adler.assecor_assessment.model.Person;
import de.adler.assecor_assessment.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class PersonServiceTest {

    private PersonRepository personRepository;
    private PersonService personService;

    @BeforeEach
    void prepareTestEnvironment() {
        this.personRepository = mock(PersonRepository.class);
        this.personService = new PersonService(personRepository);
    }

    @Test
    void getPersonById() {
        Person testPerson = new Person(1L, "Hans", "Müller", 123456, "Memmingen", ColorEnum.BLAU);
        when(personRepository.findById(1L)).thenReturn(testPerson);

        PersonResponseDTO resultPersonResponseDTO = personService.getPersonById(1L);

        Assertions.assertEquals(testPerson.getName(), resultPersonResponseDTO.getName());
    }

    @Test
    void getAllPersons() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1L, "Hans", "Müller", 123456, "Memmingen", ColorEnum.BLAU));
        personList.add(new Person(2L, "Maria", "Ohnsorg", 123456, "Angermünde", ColorEnum.ROT));

        when(personRepository.findAll()).thenReturn(personList);

        List<PersonResponseDTO> personResponseDTOS = personService.getAllPersons();

        Assertions.assertEquals(2, personResponseDTOS.size());
        Assertions.assertEquals("Hans", personResponseDTOS.get(0).getName());
        Assertions.assertEquals("Maria", personResponseDTOS.get(1).getName());
        Assertions.assertEquals("Müller", personResponseDTOS.get(0).getLastname());
        Assertions.assertEquals("Ohnsorg", personResponseDTOS.get(1).getLastname());
    }

    @Test
    void getPersonsByColor() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1L, "Hans", "Müller", 123456, "Memmingen", ColorEnum.BLAU));

        when(personRepository.findByColor(ColorEnum.BLAU)).thenReturn(personList);

        List<PersonResponseDTO> personResponseDTOS = personService.getPersonsByColor("blau");

        Assertions.assertEquals(1, personResponseDTOS.size());
        Assertions.assertEquals("Hans", personResponseDTOS.getFirst().getName());
        Assertions.assertEquals("Müller", personResponseDTOS.getFirst().getLastname());
        Assertions.assertEquals(123456, personResponseDTOS.getFirst().getZipcode());
        Assertions.assertEquals("Memmingen", personResponseDTOS.getFirst().getCity());
        Assertions.assertEquals("blau", personResponseDTOS.getFirst().getColor());
    }

    @Test
    void addPerson() {
        PersonRequestDTO testPersonRequestDTO = new PersonRequestDTO("Hans", "Müller", 123456, "Memmingen", 1);

        personService.addPerson(testPersonRequestDTO);

        verify(personRepository).savePerson(PersonMapper.toPerson(testPersonRequestDTO));
    }
}
