package de.adler.assecor_assessment.repository;

import de.adler.assecor_assessment.model.ColorEnum;
import de.adler.assecor_assessment.model.Person;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Profile("db")
@Repository
public class DbPersonRepository implements PersonRepository {

    private final JpaPersonRepository jpaPersonRepository;

    public DbPersonRepository(JpaPersonRepository jpaPersonRepository) {
        this.jpaPersonRepository = jpaPersonRepository;
    }

    @Override
    public Person findById(Long id) {
        return jpaPersonRepository.findById(id).orElse(null);
    }

    @Override
    public List<Person> findAll() {
        return jpaPersonRepository.findAll();
    }

    @Override
    public List<Person> findByColor(ColorEnum color) {
        return jpaPersonRepository.findByColor(color);
    }

    @Override
    public void savePerson(Person person) {
        jpaPersonRepository.save(person);
    }
}
