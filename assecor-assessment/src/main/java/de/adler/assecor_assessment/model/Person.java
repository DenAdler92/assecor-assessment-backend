package de.adler.assecor_assessment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    private Long id;
    private String name;
    private String lastName;
    private int zipcode;
    private String city;
    private ColorEnum color;
}
