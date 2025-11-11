package de.adler.assecor_assessment.mapper;

import de.adler.assecor_assessment.dto.PersonRequestDTO;
import de.adler.assecor_assessment.dto.PersonResponseDTO;
import de.adler.assecor_assessment.model.ColorEnum;
import de.adler.assecor_assessment.model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PersonMapperTest {

    @Test
    public void toPersonResponseDTOTest() {
        Person testPerson = new Person(
                1L,
                "Maria",
                "Müller",
                123456,
                "Angermünde", ColorEnum.ROT);

        PersonResponseDTO testPersonResponse = PersonMapper.toPersonResponseDTO(testPerson);

        Assertions.assertEquals(1L, testPersonResponse.getId());
        Assertions.assertEquals("Maria", testPersonResponse.getName());
        Assertions.assertEquals("Müller", testPersonResponse.getLastname());
        Assertions.assertEquals(123456, testPersonResponse.getZipcode());
        Assertions.assertEquals("Angermünde", testPersonResponse.getCity());
        Assertions.assertEquals("rot", testPersonResponse.getColor());
    }

    @Test
    public void toPersonTest() {
        PersonRequestDTO personRequestDTO = new PersonRequestDTO(
                "Waltraud",
                "Rohwald",
                123456,
                "Jena",
                2);

        Person testPerson = PersonMapper.toPerson(personRequestDTO, 1L);

        Assertions.assertEquals(1L, testPerson.getId());
        Assertions.assertEquals("Waltraud", testPerson.getName());
        Assertions.assertEquals("Rohwald", testPerson.getLastname());
        Assertions.assertEquals(123456, testPerson.getZipcode());
        Assertions.assertEquals("Jena", testPerson.getCity());
        Assertions.assertEquals(ColorEnum.GRUEN, testPerson.getColor());
    }

    @Test
    public void toPersonResponsDTOListTest() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1L, "Hans", "Müller", 123456, "Memmingen", ColorEnum.BLAU));
        personList.add(new Person(1L, "Maria", "Ohnsorg", 123456, "Angermünde", ColorEnum.ROT));

        List<PersonResponseDTO> personResponseDTOS = PersonMapper.toPersonResponseDTOList(personList);

        Assertions.assertEquals(2, personResponseDTOS.size());
        Assertions.assertEquals("Hans", personResponseDTOS.get(0).getName());
        Assertions.assertEquals("Maria", personResponseDTOS.get(1).getName());
        Assertions.assertEquals("Müller", personResponseDTOS.get(0).getLastname());
        Assertions.assertEquals("Ohnsorg", personResponseDTOS.get(1).getLastname());
    }
}
