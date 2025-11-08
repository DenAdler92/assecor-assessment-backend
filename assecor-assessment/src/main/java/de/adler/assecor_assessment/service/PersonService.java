package de.adler.assecor_assessment.service;

import de.adler.assecor_assessment.model.Person;
import de.adler.assecor_assessment.repository.CsvPersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final CsvPersonRepository csvPersonRepository;

    public PersonService(CsvPersonRepository csvPersonRepository) {
        this.csvPersonRepository = csvPersonRepository;
    }

    public Person getPersonById(Long id) {
        return csvPersonRepository.findPersonById(id);
    }

    public List<Person> getAllPersons() {
        return csvPersonRepository.findAllPersons();
    }
}
