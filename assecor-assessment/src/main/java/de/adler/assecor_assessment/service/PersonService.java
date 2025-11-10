package de.adler.assecor_assessment.service;

import de.adler.assecor_assessment.dto.PersonRequestDTO;
import de.adler.assecor_assessment.dto.PersonResponseDTO;
import de.adler.assecor_assessment.mapper.PersonMapper;
import de.adler.assecor_assessment.model.ColorEnum;
import de.adler.assecor_assessment.model.Person;
import de.adler.assecor_assessment.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public PersonResponseDTO getPersonById(Long id) {
        return PersonMapper.toPersonResponseDTO(personRepository.findById(id));
    }

    public List<PersonResponseDTO> getAllPersons() {
        return PersonMapper.toPersonResponseDTOList(personRepository.findAll());
    }

    public List<PersonResponseDTO> getPersonsByColor(String color) {
        return PersonMapper.toPersonResponseDTOList(personRepository.findByColor(ColorEnum.fromDisplayName(color)));
    }

    public void addPerson(PersonRequestDTO person) {
        Long newId = personRepository.findAll().stream()
                .mapToLong(Person::getId)
                .max()
                .orElse(0L) + 1;
        personRepository.savePerson(PersonMapper.toPerson(person, newId));
    }
}
