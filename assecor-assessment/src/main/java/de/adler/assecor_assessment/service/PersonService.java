package de.adler.assecor_assessment.service;

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

    public Person getPersonById(Long id) {
        return personRepository.findById(id);
    }

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public List<Person> getPersonsByColor(String color) {
        return personRepository.findByColor(color);
    }

    public void addPerson(Person person) {
        personRepository.savePerson(person);
    }
}
