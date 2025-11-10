package de.adler.assecor_assessment.repository;

import de.adler.assecor_assessment.model.ColorEnum;
import de.adler.assecor_assessment.model.Person;

import java.util.List;


public interface PersonRepository {
    Person findById(Long id);
    List<Person> findAll();
    List<Person> findByColor(ColorEnum color);
    void savePerson(Person person);
}
