package de.adler.assecor_assessment.controller;

import de.adler.assecor_assessment.dto.PersonResponseDTO;
import de.adler.assecor_assessment.model.Person;
import de.adler.assecor_assessment.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<PersonResponseDTO>> getAllPersons() {
        return ResponseEntity.ok(personService.getAllPersons());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonResponseDTO> getPersonById(@PathVariable Long id) {
        PersonResponseDTO personResponseDTO = personService.getPersonById(id);
        if (personResponseDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(personResponseDTO);
    }

    @GetMapping("/color/{color}")
    public ResponseEntity<List<PersonResponseDTO>> getPersonsByColor(@PathVariable String color) {
        return ResponseEntity.ok(personService.getPersonsByColor(color));
    }

    @PostMapping
    public ResponseEntity<String> addPerson(@RequestBody Person person) {
        personService.addPerson(person);
        return ResponseEntity.ok("Person added");
    }
}
