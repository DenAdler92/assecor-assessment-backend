package de.adler.assecor_assessment.repository;

import de.adler.assecor_assessment.model.Person;
import de.adler.assecor_assessment.util.CsvFilePersonManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CsvFilePersonManagerTest {

    private String testCsvPath = "test-person.csv";

    @Test
    void readFromCsvTest() throws IOException {
        String csvContent = "Müller, Hans, 67742 Lauterecken, 1\nPetersen, Peter, 18439 Stralsund, 2\n";
        Files.writeString(new File(testCsvPath).toPath(), csvContent, StandardCharsets.UTF_8);

        CsvPersonRepository csvPersonRepository = new CsvPersonRepository();

        csvPersonRepository.setCsvPath(testCsvPath);

        List<Person> personList = CsvFilePersonManager.readFromCsv(testCsvPath);
        assertEquals(2, personList.size());
    }

    @Test
    void readFromInconsistentCsvTest() throws IOException {
        String csvContent = "Andersson, Anders, 32132 Schweden - ☀, 2\nBart, Bertram, \n12313 Wasweißich, 1 \nGerber, Gerda, 76535 Woanders, 3 ";
        Files.writeString(new File(testCsvPath).toPath(), csvContent, StandardCharsets.UTF_8);

        CsvPersonRepository csvPersonRepository = new CsvPersonRepository();

        csvPersonRepository.setCsvPath(testCsvPath);

        List<Person> personList = CsvFilePersonManager.readFromCsv(testCsvPath);
        assertEquals(3, personList.size());
    }

    @Test
    void initTest() throws IOException {
        String csvContent = "Andersson, Anders, 32132 Schweden - ☀, 2\nBart, Bertram, \n12313 Wasweißich, 1 \nGerber, Gerda, 76535 Woanders, 3 ";
        Files.writeString(new File(testCsvPath).toPath(), csvContent, StandardCharsets.UTF_8);

        CsvPersonRepository csvPersonRepository = new CsvPersonRepository();

        csvPersonRepository.setCsvPath(testCsvPath);

        csvPersonRepository.init();
        assertEquals(3, csvPersonRepository.getPersonList().size());
    }

    @AfterEach
    void deleteTestFiles() throws IOException {
        Files.deleteIfExists(new File(testCsvPath).toPath());
    }
}
