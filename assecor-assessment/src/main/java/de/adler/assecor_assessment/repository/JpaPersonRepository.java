package de.adler.assecor_assessment.repository;

import de.adler.assecor_assessment.model.ColorEnum;
import de.adler.assecor_assessment.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaPersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByColor(ColorEnum color);
}
