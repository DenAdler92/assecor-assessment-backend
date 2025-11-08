package de.adler.assecor_assessment.repository;

import de.adler.assecor_assessment.model.ColorEnum;
import de.adler.assecor_assessment.model.Person;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvPersonRepository implements PersonRepository {

    @Setter
    private String csvPath = "src/main/resources/sample-input.csv";
    @Getter
    private final List<Person> personList = new ArrayList<>();

    @PostConstruct
    public void init() {
        personList.addAll(readFromCsv());
    }

    public List<Person> readFromCsv() {
        List<Person> csvPersons = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(csvPath), StandardCharsets.UTF_8))) {
            Long id = 0L;
            String line;
            String incompleteLine = "";
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!isCsvLineValid(line)) {
                    if (incompleteLine.isEmpty()) {
                        incompleteLine = incompleteLine.concat(line);
                    } else {
                        incompleteLine = incompleteLine.concat(line);
                        if (isCsvLineValid(incompleteLine)) {
                            id++;
                            csvPersons.add(parseLineToPerson(id, incompleteLine));
                            incompleteLine = "";
                        }
                    }
                } else {
                    id++;
                    csvPersons.add(parseLineToPerson(id, line));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while reading CSV", e);
        }
        return csvPersons;
    }

    private boolean isCsvLineValid(String line) {
        if (line.chars().filter(c -> c == ',').count() < 3) {
            return false;
        } else {
            return true;
        }
    }

    private Person parseLineToPerson(Long id, String csvLine) {
        String[] personAttributes = csvLine.split(",");
        String lastName = personAttributes[0].trim();
        String name = personAttributes[1].trim();
        String[] zipCodeAndCity = personAttributes[2].trim().split(" ", 2);
        int zipCode = Integer.parseInt(zipCodeAndCity[0]);
        String city = zipCodeAndCity[1];

        ColorEnum color = ColorEnum.fromColorCode(Integer.parseInt(personAttributes[3].trim()));

        return new Person(id, lastName, name, zipCode, city, color);
    }

    @Override
    public Person findPersonById(Long id) {
        return personList.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Person> findAllPersons() {
        return personList;
    }
}
