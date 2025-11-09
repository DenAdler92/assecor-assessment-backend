package de.adler.assecor_assessment.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Person {

    @Id
    private Long id;
    private String name;
    private String lastname;
    private int zipcode;
    private String city;
    private ColorEnum color;
}
