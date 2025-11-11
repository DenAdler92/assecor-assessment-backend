package de.adler.assecor_assessment.repository;

import de.adler.assecor_assessment.model.ColorEnum;
import de.adler.assecor_assessment.model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class DbPersonRepositoryTest {

    private JpaPersonRepository jpaPersonRepository;
    private DbPersonRepository dbPersonRepository;

    @BeforeEach
    public void prepareTestEnvironment() {
        jpaPersonRepository = mock(JpaPersonRepository.class);
        this.dbPersonRepository = new DbPersonRepository(jpaPersonRepository);
    }

    @Test
    void findByIdReturnResult() {
        Person testPerson = new Person(1L, "Maria", "Müller", 123456, "Angermünde", ColorEnum.ROT);
        when(jpaPersonRepository.findById(1L)).thenReturn(Optional.of(testPerson));

        Person resultPerson = dbPersonRepository.findById(1L);

        Assertions.assertEquals(testPerson, resultPerson);
        verify(jpaPersonRepository).findById(1L);
    }

    @Test
    void findByIdReturnNull() {
        when(jpaPersonRepository.findById(1L)).thenReturn(Optional.empty());

        Person resultPerson = dbPersonRepository.findById(1L);

        Assertions.assertNull(resultPerson);
        verify(jpaPersonRepository).findById(1L);
    }

    @Test
    void findAllTest() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1L, "Hans", "Müller", 123456, "Memmingen", ColorEnum.BLAU));
        personList.add(new Person(1L, "Maria", "Ohnsorg", 123456, "Angermünde", ColorEnum.ROT));

        when(jpaPersonRepository.findAll()).thenReturn(personList);

        List<Person> resultList = dbPersonRepository.findAll();

        Assertions.assertEquals(2, resultList.size());
        Assertions.assertEquals("Hans", resultList.get(0).getName());
        Assertions.assertEquals("Maria", resultList.get(1).getName());
        Assertions.assertEquals("Müller", resultList.get(0).getLastname());
        Assertions.assertEquals("Ohnsorg", resultList.get(1).getLastname());
        verify(jpaPersonRepository).findAll();
    }

    @Test
    void findAllNoEntriesTest() {
        List<Person> personList = new ArrayList<>();
        when(jpaPersonRepository.findAll()).thenReturn(personList);

        List<Person> resultList = dbPersonRepository.findAll();

        Assertions.assertEquals(0, resultList.size());
        verify(jpaPersonRepository).findAll();
    }

    @Test
    void findByColorTest() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1L, "Hans", "Müller", 123456, "Memmingen", ColorEnum.BLAU));

        when(jpaPersonRepository.findByColor(ColorEnum.BLAU)).thenReturn(personList);

        List<Person> resultList = dbPersonRepository.findByColor(ColorEnum.BLAU);

        Assertions.assertEquals(1, resultList.size());
        Assertions.assertEquals("Hans", resultList.getFirst().getName());
        Assertions.assertEquals("Müller", resultList.getFirst().getLastname());
        verify(jpaPersonRepository).findByColor(ColorEnum.BLAU);
    }

    @Test
    void findByColorEmptyListTest() {
        List<Person> personList = new ArrayList<>();
        when(jpaPersonRepository.findByColor(ColorEnum.BLAU)).thenReturn(personList);

        List<Person> resultList = dbPersonRepository.findByColor(ColorEnum.BLAU);

        Assertions.assertEquals(0, resultList.size());
        verify(jpaPersonRepository).findByColor(ColorEnum.BLAU);
    }

    @Test
    void save() {
        Person testPerson = new Person(1L, "Maria", "Müller", 123456, "Angermünde", ColorEnum.ROT);

        dbPersonRepository.savePerson(testPerson);

        verify(jpaPersonRepository).save(testPerson);
    }
}
