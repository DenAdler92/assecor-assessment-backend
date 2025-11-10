package de.adler.assecor_assessment.service;

import de.adler.assecor_assessment.dto.PersonResponseDTO;
import de.adler.assecor_assessment.mapper.PersonMapper;
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
        return personRepository.findAll().stream().map(PersonMapper::toPersonResponseDTO).toList();
    }

    public List<PersonResponseDTO> getPersonsByColor(String color) {
        return personRepository.findByColor(color).stream().map(PersonMapper::toPersonResponseDTO).toList();
    }

    public void addPerson(Person person) {
        personRepository.savePerson(person);
    }
}
