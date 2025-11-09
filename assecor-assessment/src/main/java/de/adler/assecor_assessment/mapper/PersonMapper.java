package de.adler.assecor_assessment.mapper;

import de.adler.assecor_assessment.dto.PersonResponseDTO;
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
}
