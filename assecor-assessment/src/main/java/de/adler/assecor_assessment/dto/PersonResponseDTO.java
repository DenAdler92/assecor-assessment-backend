package de.adler.assecor_assessment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonResponseDTO {

    private Long id;
    private String name;
    private String lastname;
    private int zipcode;
    private String city;
    private String color;
}
