package br.com.farias.rest_com_springboot_e_java.repositories;

import br.com.farias.rest_com_springboot_e_java.integrationtests.testcontainers.AbstractIntegrationTest;
import br.com.farias.rest_com_springboot_e_java.model.Person;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PersonRepositoryTest extends AbstractIntegrationTest {

    @Autowired
    private PersonRepository repository;

    private Person person0;

    @BeforeEach
    public void setup(){
        // Given
        person0 = new Person(
                "Marcelo",
                "Farias",
                "QNN 18 Conjunto B - Ceilândia Sul",
                "Male",
                "Farias@gmail.com");
    }

    @DisplayName("Given Person Object when Save then Return Saved Person")
    @Test
    void testGivenPersonObject_whenSave_thenReturnSavedPerson(){

        // When
        Person savedPerson = repository.save(person0);

        // Then
        assertNotNull(savedPerson);
        assertTrue(savedPerson.getId() > 0);
    }

    @DisplayName("Given Person List when FindAll then Return Person List")
    @Test
    void testGivenPersonList_whenFindAll_thenReturnPersonList(){

         Person person1 = new Person(
                "Leandro",
                "Costa",
                "Uberlândia - Minas Gerais - Brasil",
                "Male",
                "leandro@gmail.com");
        repository.save(person0);
        repository.save(person1);

        // When
        List<Person> personList = repository.findAll();
        // Then
        assertNotNull(personList);
        assertEquals(2,personList.size());
    }

    @DisplayName("Given Person Object when FindById then Return Person Object")
    @Test
    void testGivenPersonObject_whenFindById_thenReturnPersonObject(){

        repository.save(person0);

        // When
        Person savedPerson = repository.findById(person0.getId()).get();

        // Then
        assertNotNull(savedPerson);
        assertEquals(person0.getId(),savedPerson.getId());
    }

    @DisplayName("Given Person Object when FindByEmail then Return Person Object")
    @Test
    void testGivenPersonObject_whenFindByEmail_thenReturnPersonObject(){

        repository.save(person0);

        // When
        Person savedPerson = repository.findByEmail(person0.getEmail()).get();

        // Then
        assertNotNull(savedPerson);
        assertEquals(person0.getId(),savedPerson.getId());
    }

    @DisplayName("Given Person Object when UpdatePerson then Return Updated Person Object")
    @Test
    void testGivenPersonObject_whenUpdatePerson_thenReturnUpdatedPersonObject(){

        repository.save(person0);

        // When
        Person savedPerson = repository.findById(person0.getId()).get();
        savedPerson.setFirstName("Isaac");
        savedPerson.setEmail("isaac@gmail.com");

        Person updatePerson = repository.save(savedPerson);
        // Then
        assertNotNull(updatePerson);
        assertEquals("Isaac",updatePerson.getFirstName());
        assertEquals("isaac@gmail.com",updatePerson.getEmail());
    }

    @DisplayName("Given Person Object when Delete then Remove Person")
    @Test
    void testGivenPersonObject_whenDelete_thenRemovePerson(){

        repository.save(person0);

        // When
        repository.deleteById(person0.getId());

        Optional<Person> personOptional = repository.findById(person0.getId());
        // Then
        assertTrue(personOptional.isEmpty());
    }

    @DisplayName("JUnit test Given FirstName And LastName when FindJPQL then Return Person Object")
    @Test
    void testGivenFirstNameAndLastName_whenFindJPQL_thenReturnPersonObject(){

        repository.save(person0);

        // When
        String firstName = "Marcelo";
        String lastName = "Farias";
        Person savedPerson = repository.findByJPQL(firstName, lastName);

        // Then
        assertNotNull(savedPerson);
        assertEquals(firstName,savedPerson.getFirstName());
        assertEquals(lastName,savedPerson.getLastName());
    }

    @DisplayName("JUnit test Given FirstName And LastName when FindJPQLNamedParameters then Return Person Object")
    @Test
    void testGivenFirstNameAndLastName_whenFindJPQLNamedParameters_thenReturnPersonObject(){

        repository.save(person0);

        // When
        String firstName = "Marcelo";
        String lastName = "Farias";
        Person savedPerson = repository.findByJPQLNamedParameters(firstName, lastName);

        // Then
        assertNotNull(savedPerson);
        assertEquals(firstName,savedPerson.getFirstName());
        assertEquals(lastName,savedPerson.getLastName());
    }

    @DisplayName("JUnit test Given FirstName And LastName when FindByNativeSQL then Return Person Object")
    @Test
    void testGivenFirstNameAndLastName_whenFindNativeSQL_thenReturnPersonObject(){

        repository.save(person0);

        // When
        String firstName = "Marcelo";
        String lastName = "Farias";
        Person savedPerson = repository.findByNativeSQL(firstName, lastName);

        // Then
        assertNotNull(savedPerson);
        assertEquals(firstName,savedPerson.getFirstName());
        assertEquals(lastName,savedPerson.getLastName());
    }

    @DisplayName("JUnit test Given FirstName And LastName when FindNativeSQLNamedParameters then Return Person Object")
    @Test
    void testGivenFirstNameAndLastName_whenFindJNativeSQLNamedParameters_thenReturnPersonObject(){

        repository.save(person0);

        // When
        String firstName = "Marcelo";
        String lastName = "Farias";
        Person savedPerson = repository.findByNativeSQLNamedParameters(firstName, lastName);

        // Then
        assertNotNull(savedPerson);
        assertEquals(firstName,savedPerson.getFirstName());
        assertEquals(lastName,savedPerson.getLastName());
    }
}