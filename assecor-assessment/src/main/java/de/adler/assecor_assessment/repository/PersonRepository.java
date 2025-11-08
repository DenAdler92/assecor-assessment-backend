package de.adler.assecor_assessment.repository;

import de.adler.assecor_assessment.model.Person;

import java.util.List;


public interface PersonRepository {
    Person findPersonById(Long id);
    List<Person> findAllPersons();
}
