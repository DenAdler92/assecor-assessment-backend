package de.adler.assecor_assessment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonResponseDTO {

    private Long id;
    private String name;
    private String lastname;
    private int zipcode;
    private String city;
    private String color;
}
