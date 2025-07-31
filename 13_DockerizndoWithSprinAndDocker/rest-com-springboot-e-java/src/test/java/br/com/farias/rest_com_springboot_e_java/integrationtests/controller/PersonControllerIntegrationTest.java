package br.com.farias.rest_com_springboot_e_java.integrationtests.controller;

import br.com.farias.rest_com_springboot_e_java.config.TestConfigs;
import br.com.farias.rest_com_springboot_e_java.integrationtests.testcontainers.AbstractIntegrationTest;
import br.com.farias.rest_com_springboot_e_java.model.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class PersonControllerIntegrationTest extends AbstractIntegrationTest {

    private static RequestSpecification specification;
    private static ObjectMapper objectMapper;
    private static Person person;

    @BeforeAll
    public static void setup(){
        // Given
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        specification = new RequestSpecBuilder()
                .setBasePath("/person")
                .setPort(TestConfigs.SERVER_PORT)
                    .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                    .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        person = new Person(
                "Marcelo",
                "Farias",
                "QNN 18 Conjunto B - Ceilândia Sul",
                "Male",
                "Farias@gmail.com");
    }

    @Test
    @Order(1)
    @DisplayName("JUnit integration Test Given Person Object when Create One Person Should Return A Person Object")
    void integrationTestGivenPersonObject_when_CreateOnePerson_ShouldReturnAPersonObject() throws JsonProcessingException {

        var content = given().spec(specification)
                    .contentType(TestConfigs.CONTENT_TYPE_JSON)
                    .body(person)
                .when()
                    .post()
                .then()
                    .statusCode(200)
                        .extract()
                            .body()
                                .asString();

        Person createdPerson = objectMapper.readValue(content, Person.class);

        person = createdPerson;

        Assertions.assertNotNull(createdPerson);

        Assertions.assertNotNull(createdPerson.getId());
        Assertions.assertNotNull(createdPerson.getFirstName());
        Assertions.assertNotNull(createdPerson.getLastName());
        Assertions.assertNotNull(createdPerson.getAddress());
        Assertions.assertNotNull(createdPerson.getGender());
        Assertions.assertNotNull(createdPerson.getEmail());

        Assertions.assertTrue(createdPerson.getId() > 0);
        Assertions.assertEquals("Marcelo", createdPerson.getFirstName());
        Assertions.assertEquals("Farias", createdPerson.getLastName());
        Assertions.assertEquals("QNN 18 Conjunto B - Ceilândia Sul", createdPerson.getAddress());
        Assertions.assertEquals("Male", createdPerson.getGender());
        Assertions.assertEquals("Farias@gmail.com", createdPerson.getEmail());
    }

    @Test
    @Order(2)
    @DisplayName("JUnit integration Test Given Person Object when Update One Person Should Return A UpdatedPerson Object")
    void integrationTestGivenPersonObject_when_UpdateOnePerson_ShouldReturnAUpdatedPersonObject() throws JsonProcessingException {

        person.setFirstName("Isaac");
        person.setEmail("isaac@gmail.com");
        var content = given().spec(specification)
                    .contentType(TestConfigs.CONTENT_TYPE_JSON)
                    .body(person)
                .when()
                .put()
                .then()
                    .statusCode(200)
                        .extract()
                            .body()
                                .asString();

        Person updatedPerson = objectMapper.readValue(content, Person.class);

        person = updatedPerson;

        Assertions.assertNotNull(updatedPerson);

        Assertions.assertNotNull(updatedPerson.getId());
        Assertions.assertNotNull(updatedPerson.getFirstName());
        Assertions.assertNotNull(updatedPerson.getLastName());
        Assertions.assertNotNull(updatedPerson.getAddress());
        Assertions.assertNotNull(updatedPerson.getGender());
        Assertions.assertNotNull(updatedPerson.getEmail());

        Assertions.assertTrue(updatedPerson.getId() > 0);
        Assertions.assertEquals("Isaac", updatedPerson.getFirstName());
        Assertions.assertEquals("Farias", updatedPerson.getLastName());
        Assertions.assertEquals("QNN 18 Conjunto B - Ceilândia Sul", updatedPerson.getAddress());
        Assertions.assertEquals("Male", updatedPerson.getGender());
        Assertions.assertEquals("isaac@gmail.com", updatedPerson.getEmail());
    }

    @Test
    @Order(3)
    @DisplayName("JUnit integration Test Given Person Object when findById Should Return A Person Object")
    void integrationTestGivenPersonObject_when_findByIdPerson_ShouldReturnAPersonObject() throws JsonProcessingException {

        var content = given().spec(specification)
                .pathParam("id", person.getId())
                .when()
                    .get("{id}")
                .then()
                    .statusCode(200)
                        .extract()
                            .body()
                                .asString();

        Person foundPerson = objectMapper.readValue(content, Person.class);

        Assertions.assertNotNull(foundPerson);

        Assertions.assertNotNull(foundPerson.getId());
        Assertions.assertNotNull(foundPerson.getFirstName());
        Assertions.assertNotNull(foundPerson.getLastName());
        Assertions.assertNotNull(foundPerson.getAddress());
        Assertions.assertNotNull(foundPerson.getGender());
        Assertions.assertNotNull(foundPerson.getEmail());

        Assertions.assertTrue(foundPerson.getId() > 0);
        Assertions.assertEquals("Isaac", foundPerson.getFirstName());
        Assertions.assertEquals("Farias", foundPerson.getLastName());
        Assertions.assertEquals("QNN 18 Conjunto B - Ceilândia Sul", foundPerson.getAddress());
        Assertions.assertEquals("Male", foundPerson.getGender());
        Assertions.assertEquals("isaac@gmail.com", foundPerson.getEmail());
    }

    @Test
    @Order(4)
    @DisplayName("JUnit integration Test when findAll Should Return A Person List")
    void integrationTest_when_findAllPerson_ShouldReturnAPersonList() throws JsonProcessingException {
        Person anotherPerson = new Person(
                "Giovanna",
                "Farias",
                "QNN 18 Conjunto B - Ceilândia Sul",
                "Female",
                "gigipereira@gmail.com");

        given().spec(specification)
                    .contentType(TestConfigs.CONTENT_TYPE_JSON)
                    .body(anotherPerson)
                .when()
                    .post()
                .then()
                    .statusCode(200);

        var content = given().spec(specification)
                .when()
                    .get()
                .then()
                    .statusCode(200)
                .extract()
                    .body()
                        .asString();

        Person[] myArray = objectMapper.readValue(content,Person[].class);
        List<Person> people = Arrays.asList(myArray);

        Person foundPersonOne = people.get(0);

        Assertions.assertNotNull(foundPersonOne);

        Assertions.assertNotNull(foundPersonOne.getId());
        Assertions.assertNotNull(foundPersonOne.getFirstName());
        Assertions.assertNotNull(foundPersonOne.getLastName());
        Assertions.assertNotNull(foundPersonOne.getAddress());
        Assertions.assertNotNull(foundPersonOne.getGender());
        Assertions.assertNotNull(foundPersonOne.getEmail());

        Assertions.assertTrue(foundPersonOne.getId() > 0);
        Assertions.assertEquals("Isaac", foundPersonOne.getFirstName());
        Assertions.assertEquals("Farias", foundPersonOne.getLastName());
        Assertions.assertEquals("QNN 18 Conjunto B - Ceilândia Sul", foundPersonOne.getAddress());
        Assertions.assertEquals("Male", foundPersonOne.getGender());
        Assertions.assertEquals("isaac@gmail.com", foundPersonOne.getEmail());

        Person foundPersonTwo = people.get(1);

        Assertions.assertNotNull(foundPersonTwo);

        Assertions.assertNotNull(foundPersonTwo.getId());
        Assertions.assertNotNull(foundPersonTwo.getFirstName());
        Assertions.assertNotNull(foundPersonTwo.getLastName());
        Assertions.assertNotNull(foundPersonTwo.getAddress());
        Assertions.assertNotNull(foundPersonTwo.getGender());
        Assertions.assertNotNull(foundPersonTwo.getEmail());

        Assertions.assertTrue(foundPersonTwo.getId() > 0);
        Assertions.assertEquals("Giovanna", foundPersonTwo.getFirstName());
        Assertions.assertEquals("Farias", foundPersonTwo.getLastName());
        Assertions.assertEquals("QNN 18 Conjunto B - Ceilândia Sul", foundPersonTwo.getAddress());
        Assertions.assertEquals("Female", foundPersonTwo.getGender());
        Assertions.assertEquals("gigipereira@gmail.com", foundPersonTwo.getEmail());
    }

    @Test
    @Order(5)
    @DisplayName("JUnit integration Test Given Person Object when Delete Should Return No Content")
    void integrationTestGivenPersonObject_when_Delete_ShouldReturnNoContent() throws JsonProcessingException {

        given().spec(specification)
                    .pathParam("id", person.getId())
                .when()
                    .delete("{id}")
                .then()
                    .statusCode(204);


    }
}