package de.adler.assecor_assessment.repository;

import de.adler.assecor_assessment.model.ColorEnum;
import de.adler.assecor_assessment.model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CsvPersonRepositoryTest {

    private CsvPersonRepository csvPersonRepository;
    private String testCsvPath;

    @BeforeEach
    void prepareTestEnvironment() throws IOException {
        this.csvPersonRepository = new CsvPersonRepository();
        testCsvPath = "test-person.csv";
        String csvContent = "Andersson, Anders, 32132 Schweden - ☀, 2\nBart, Bertram, \n12313 Wasweißich, 1 \nGerber, Gerda, 76535 Woanders, 1 ";
        Files.writeString(new File(testCsvPath).toPath(), csvContent, StandardCharsets.UTF_8);
        csvPersonRepository.setCsvPath(testCsvPath);

    }

    @Test
    void initTest() {
        csvPersonRepository.init();
        assertEquals(3, csvPersonRepository.getPersonList().size());
    }

    @Test
    void findById() {
        csvPersonRepository.init();

        Person resultPerson = csvPersonRepository.findById(2L);

        Assertions.assertEquals("Bart", resultPerson.getLastname());
        Assertions.assertEquals("Bertram", resultPerson.getName());
        Assertions.assertEquals(12313, resultPerson.getZipcode());
        Assertions.assertEquals("Wasweißich", resultPerson.getCity());
        Assertions.assertEquals(ColorEnum.BLAU, resultPerson.getColor());
    }

    @Test
    void findAll() {
        csvPersonRepository.init();

        List<Person> resultPersons = csvPersonRepository.findAll();

        Assertions.assertEquals(3, resultPersons.size());
        Assertions.assertEquals("Andersson", resultPersons.get(0).getLastname());
        Assertions.assertEquals("Bart", resultPersons.get(1).getLastname());
        Assertions.assertEquals("Gerber", resultPersons.get(2).getLastname());
    }

    @Test
    void findByColor() {
        csvPersonRepository.init();

        List<Person> resultPersons = csvPersonRepository.findByColor(ColorEnum.BLAU);

        Assertions.assertEquals(2, resultPersons.size());
        Assertions.assertEquals("Bart", resultPersons.get(0).getLastname());
        Assertions.assertEquals("Gerber", resultPersons.get(1).getLastname());
    }

    @AfterEach
    void deleteTestFiles() throws IOException {
        Files.deleteIfExists(new File(testCsvPath).toPath());
    }
}
