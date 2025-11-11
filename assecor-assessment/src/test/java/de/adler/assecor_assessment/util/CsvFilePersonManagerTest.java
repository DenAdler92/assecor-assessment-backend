package de.adler.assecor_assessment.util;

import de.adler.assecor_assessment.model.ColorEnum;
import de.adler.assecor_assessment.model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CsvFilePersonManagerTest {

    private String testCsvPath = "test-person.csv";

    @Test
    void readFromCsvTest() throws IOException {
        String csvContent = "Müller, Hans, 67742 Lauterecken, 1\nPetersen, Peter, 18439 Stralsund, 2\n";
        Files.writeString(new File(testCsvPath).toPath(), csvContent, StandardCharsets.UTF_8);
        List<Person> personList = CsvFilePersonManager.readFromCsv(testCsvPath);
        assertEquals(2, personList.size());
    }

    @Test
    void readFromInconsistentCsvTest() throws IOException {
        String csvContent = "Andersson, Anders, 32132 Schweden - ☀, 2\nBart\n, Bertram, \n12313 Wasweißich, 1 \nGerber, Gerda, 76535 Woanders, 3 ";
        Files.writeString(new File(testCsvPath).toPath(), csvContent, StandardCharsets.UTF_8);
        List<Person> personList = CsvFilePersonManager.readFromCsv(testCsvPath);
        assertEquals(3, personList.size());
    }

    @Test
    void readFromMultipleInconsistentCsvTest() throws IOException {
        String csvContent = "Andersson, Anders, 32132 Schweden - ☀, 2\nBart, Bertram, \n12313 Wasweißich, 1 \nGerber, Gerda, 76535 Woanders, 3 ";
        Files.writeString(new File(testCsvPath).toPath(), csvContent, StandardCharsets.UTF_8);
        List<Person> personList = CsvFilePersonManager.readFromCsv(testCsvPath);
        assertEquals(3, personList.size());
    }

    @Test
    void readFromNoneExistingCsv() {
        assertThrows(RuntimeException.class, () -> CsvFilePersonManager.readFromCsv(testCsvPath));
    }

    @Test
    void writeToCsvTest() {
        new File(testCsvPath);
        Person testPerson = new Person(1L, "Müller", "Hans", 32323, "Berlin", ColorEnum.BLAU);

        CsvFilePersonManager.writeToCsv(testPerson, testCsvPath);

        List<Person> testPersonList = CsvFilePersonManager.readFromCsv(testCsvPath);

        Assertions.assertEquals(testPerson.getName(), testPersonList.getFirst().getName());
        Assertions.assertEquals(testPerson.getLastname(), testPersonList.getFirst().getLastname());
        Assertions.assertEquals(testPerson.getZipcode(), testPersonList.getFirst().getZipcode());
        Assertions.assertEquals(testPerson.getCity(), testPersonList.getFirst().getCity());
        Assertions.assertEquals(testPerson.getColor(), testPersonList.getFirst().getColor());
    }

    @Test
    void writeToNotExistingCsvTest() {
        String invalidPath = "notExistingFolder/test.csv";
        Person testPerson = new Person(1L, "Müller", "Hans", 32323, "Berlin", ColorEnum.BLAU);
        Assertions.assertThrows(RuntimeException.class, () -> CsvFilePersonManager.writeToCsv(testPerson, invalidPath));
    }

    @AfterEach
    void deleteTestFiles() throws IOException {
        Files.deleteIfExists(new File(testCsvPath).toPath());
    }
}
