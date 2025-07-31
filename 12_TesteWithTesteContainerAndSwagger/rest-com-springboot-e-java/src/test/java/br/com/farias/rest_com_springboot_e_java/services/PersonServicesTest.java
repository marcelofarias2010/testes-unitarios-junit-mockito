package br.com.farias.rest_com_springboot_e_java.services;

import br.com.farias.rest_com_springboot_e_java.exceptions.ResourceNotFoundException;
import br.com.farias.rest_com_springboot_e_java.model.Person;
import br.com.farias.rest_com_springboot_e_java.repositories.PersonRepository;

import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PersonServicesTest {

    @Mock
    private PersonRepository repository;
    @InjectMocks
    private PersonServices services;

    private Person person0;

    @BeforeEach
    void setUp() {
        // Give / Arrange
        person0 = new Person(
                "Marcelo",
                "Farias",
                "QNN 18 Conjunto B - Ceilândia Sul",
                "Male",
                "Farias@gmail.com");
    }

    @DisplayName("JUnit test for Given Object when save Person then return person object")
    @Test
    void textGivenPersonObject_WhenSavedPerson_theReturnPersonObject() {
        // Given / Arrange
        given(repository.findByEmail(anyString())).willReturn(Optional.empty());
        given(repository.save(person0)).willReturn(person0);

        // When / Act
        Person savedPerson = services.create(person0);

        // Then / Assert
        assertNotNull(savedPerson);
        assertEquals("Marcelo", savedPerson.getFirstName());
    }

    @DisplayName("JUnit test for Given Existing when saved Person then Throws Exception")
    @Test
    void textGivenExistingEmail_WhenSavedPerson_thenThrowsException() {
        // Given(dado) / Arrange(organizar)
        given(repository.findByEmail(anyString())).willReturn(Optional.of(person0));

        // When(quando) / Act(agir)
        assertThrows(ResourceNotFoundException.class, () -> {
            services.create(person0);
        });

        // Then(então) / Assert(afirmar)
        verify(repository, never()).save(any(Person.class));
    }

    @DisplayName("JUnit test for Given Person List when FindAllPerson then Return Persons")
    @Test
    void textGivenPersonsList_WhenFindAllPerson_thenReturnPersons() {
        // Given(dado) / Arrange(organizar)
        Person person1 = new Person(
                "Leandro",
                "Costa",
                "Uberlândia - Minas Gerais - Brasil",
                "Male",
                "leandro@gmail.com");

        given(repository.findAll()).willReturn(List.of(person0, person1));

        // When(quando) / Act(agir)
        List<Person> personList = services.findAll();

        // Then(então) / Assert(afirmar)
        assertNotNull(personList);
        assertEquals(2, personList.size());
    }

    @DisplayName("JUnit test for Given Empty Person List when FindAllPerson then Return Empty Persons List")
    @Test
    void textGivenEmptyPersonsList_WhenFindAllPerson_thenReturnEmptyPersonsList() {
        // Given(dado) / Arrange(organizar)

        given(repository.findAll()).willReturn(Collections.emptyList());

        // When(quando) / Act(agir)
        List<Person> personList = services.findAll();

        // Then(então) / Assert(afirmar)
        assertTrue(personList.isEmpty());
        assertEquals(0, personList.size());
    }

    @DisplayName("JUnit test for Given Person Id when FindById then return person object")
    @Test
    void textGivenPersonId_WhenFindById_theReturnPersonObject() {
        // Given(dado) / Arrange(organizar)
        given(repository.findById(anyLong())).willReturn(Optional.of(person0));

        // When(quando) / Act(agir)
        Person savedPerson = services.findById(1L);

        // Then(então) / Assert(afirmar)
        assertNotNull(savedPerson);
        assertEquals("Marcelo", savedPerson.getFirstName());
    }

    @DisplayName("JUnit test for Given Person Object Person when Update Person then return Updated person object")
    @Test
    void textGivenPersonObject_WhenUpdatePerson_theReturnUpdatePersonObject() {
        // Given(dado) / Arrange(organizar)
        person0.setId(1L);
        given(repository.findById(anyLong())).willReturn(Optional.of(person0));
        person0.setEmail("leonardo@erudio.com.br");
        person0.setFirstName("Leonardo");

        given(repository.save(person0)).willReturn(person0);
        // When(quando) / Act(agir)
        Person updatedPerson = services.update(person0);

        // Then(então) / Assert(afirmar)
        assertNotNull(updatedPerson);
        assertEquals("Leonardo", updatedPerson.getFirstName());
        assertEquals("leonardo@erudio.com.br", updatedPerson.getEmail());
    }

    @DisplayName("JUnit test for Given Person Id when Delete Person then Do Nothing")
    @Test
    void textGivenPersonId_WhenDeletePerson_theDoNothing() {
        // Given(dado) / Arrange(organizar)
        person0.setId(1L);
        given(repository.findById(anyLong())).willReturn(Optional.of(person0));
        willDoNothing().given(repository).delete(person0);

        // When(quando) / Act(agir)
        services.delete(person0.getId());

        // Then(então) / Assert(afirmar)
        verify(repository,times(1)).delete(person0);
    }

}