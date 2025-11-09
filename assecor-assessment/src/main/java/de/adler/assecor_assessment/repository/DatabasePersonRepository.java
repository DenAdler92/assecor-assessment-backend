package de.adler.assecor_assessment.repository;

import de.adler.assecor_assessment.model.Person;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Profile("db")
@Repository
public interface DatabasePersonRepository extends JpaRepository<Person, Integer>, PersonRepository {
    List<Person> findByColor(String color);
}
