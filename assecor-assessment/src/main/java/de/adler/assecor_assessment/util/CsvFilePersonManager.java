package de.adler.assecor_assessment.util;

import de.adler.assecor_assessment.model.ColorEnum;
import de.adler.assecor_assessment.model.Person;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CsvFilePersonManager {

    public static List<Person> readFromCsv(String csvPath) {
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

    private static boolean isCsvLineValid(String line) {
        if (line.chars().filter(c -> c == ',').count() < 3) {
            return false;
        } else {
            return true;
        }
    }

    private static Person parseLineToPerson(Long id, String csvLine) {
        String[] personAttributes = csvLine.split(",");
        String lastName = personAttributes[0].trim();
        String name = personAttributes[1].trim();
        String[] zipCodeAndCity = personAttributes[2].trim().split(" ", 2);
        int zipCode = Integer.parseInt(zipCodeAndCity[0]);
        String city = zipCodeAndCity[1];

        ColorEnum color = ColorEnum.fromColorCode(Integer.parseInt(personAttributes[3].trim()));

        return new Person(id, lastName, name, zipCode, city, color);
    }

    public static void writeToCsv(Person p, String csvPath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvPath, true))) {
            writer.write(String.format(
                    "%n%s, %s, %d %s, %d",
                    p.getLastname(),
                    p.getName(),
                    p.getZipcode(),
                    p.getCity(),
                    p.getColor().getColorCode()
            ));
        } catch (IOException e) {
            throw new RuntimeException("Error writing in Csv-File", e);
        }
    }
}
