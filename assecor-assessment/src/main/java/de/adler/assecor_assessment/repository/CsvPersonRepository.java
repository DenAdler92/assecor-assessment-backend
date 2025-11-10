package de.adler.assecor_assessment.repository;

import de.adler.assecor_assessment.model.ColorEnum;
import de.adler.assecor_assessment.model.Person;
import de.adler.assecor_assessment.util.CsvFilePersonManager;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Profile("csv")
@Repository
public class CsvPersonRepository implements PersonRepository {

    @Setter
    private String csvPath = "src/main/resources/sample-input.csv";
    @Getter
    private final List<Person> personList = new ArrayList<>();

    @PostConstruct
    public void init() {
        personList.addAll(CsvFilePersonManager.readFromCsv(csvPath));
    }

    @Override
    public Person findById(Long id) {
        return personList.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Person> findAll() {
        return personList;
    }

    @Override
    public List<Person> findByColor(ColorEnum color) {
        return personList.stream()
                .filter(p-> p.getColor().getDisplayName().equals(color.getDisplayName()))
                .toList();
    }

    @Override
    public void savePerson(Person person) {
        Long newId = personList.stream()
                .mapToLong(Person::getId)
                .max()
                .orElse(0L) + 1;
        person.setId(newId);
        personList.add(person);
        CsvFilePersonManager.writeToCsv(person, csvPath);
    }
}
