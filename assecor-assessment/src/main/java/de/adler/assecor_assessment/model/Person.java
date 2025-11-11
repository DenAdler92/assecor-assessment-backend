package de.adler.assecor_assessment.model;

import de.adler.assecor_assessment.converter.ColorEnumConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastname;
    private int zipcode;
    private String city;
    @Convert(converter = ColorEnumConverter.class)
    private ColorEnum color;

    public Person(String name, String lastname, int zipcode, String city, ColorEnum color) {
        this.name = name;
        this.lastname = lastname;
        this.zipcode = zipcode;
        this.city = city;
        this.color = color;
    }
}
