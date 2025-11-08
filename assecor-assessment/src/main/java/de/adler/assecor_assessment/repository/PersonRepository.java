package de.adler.assecor_assessment.repository;

import de.adler.assecor_assessment.model.Person;


public interface PersonRepository {
    Person findPersonById(Long id);
}
