package de.adler.assecor_assessment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonRequestDTO {

    private String name;
    private String lastname;
    private int zipcode;
    private String city;
    private int color;
}
