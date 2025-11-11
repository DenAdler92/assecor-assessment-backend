package de.adler.assecor_assessment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonRequestDTO {

    private String name;
    private String lastname;
    private int zipcode;
    private String city;
    private int color;
}
