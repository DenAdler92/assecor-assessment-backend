package de.adler.assecor_assessment.mapper;

import de.adler.assecor_assessment.dto.PersonRequestDTO;
import de.adler.assecor_assessment.dto.PersonResponseDTO;
import de.adler.assecor_assessment.model.ColorEnum;
import de.adler.assecor_assessment.model.Person;

public class PersonMapper {

    public static PersonResponseDTO toPersonResponseDTO(Person person) {
        PersonResponseDTO personResponseDTO = new PersonResponseDTO();
        personResponseDTO.setId(person.getId());
        personResponseDTO.setName(person.getName());
        personResponseDTO.setLastname(person.getLastname());
        personResponseDTO.setZipcode(person.getZipcode());
        personResponseDTO.setCity(person.getCity());
        personResponseDTO.setColor(person.getColor().getDisplayName());
        return personResponseDTO;
    }

    public static Person toPerson(PersonRequestDTO personRequestDTO, Long id) {
        Person person = new Person();
        person.setId(id);
        person.setName(personRequestDTO.getName());
        person.setLastname(personRequestDTO.getLastname());
        person.setZipcode(personRequestDTO.getZipcode());
        person.setCity(personRequestDTO.getCity());
        person.setColor(ColorEnum.fromColorCode(personRequestDTO.getColor()));
        return person;
    }
}
