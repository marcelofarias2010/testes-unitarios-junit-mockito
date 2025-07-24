package br.com.farias.business;

import br.com.farias.service.CourseService;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mockito;


import java.util.Arrays;
import java.util.List;

class CourseBusinessMockWithBDDTest {

    CourseService mockService;
    CourseBusiness business;
    List<String> courses;

    @BeforeEach
    void setup() {
        // Given / Arrange
        mockService = Mockito.mock(CourseService.class);
        business = new CourseBusiness(mockService);
        courses = Arrays.asList(
                "REST API's RESTFul do 0 à Azure com ASP.NET Core 5 e Docker",
                "Agile Desmistificado com Scrum, XP, Kanban e Trello",
                "Spotify Engineering Culture Desmistificado",
                "REST API's RESTFul do 0 à AWS com Spring Boot 3 Java e Docker",
                "Docker do Zero à Maestria - Contêinerização Desmistificada",
                "Docker para Amazon AWS Implante Apps Java e .NET com Travis CI",
                "Microsserviços do 0 com Spring Cloud, Spring Boot e Docker",
                "Arquitetura de Microsserviços do 0 com ASP.NET, .NET 6 e C#",
                "REST API's RESTFul do 0 à AWS com Spring Boot 3 Kotlin e Docker",
                "Kotlin para DEV's Java: Aprenda a Linguagem Padrão do Android",
                "Microsserviços do 0 com Spring Cloud, Kotlin e Docker"
        );
    }

    @Test
    void testCourseRelatedToSpring_When_UsingAMock() {
        // Given / Arrange
        BDDMockito.given(mockService.retrieveCourses("Leandro")).willReturn(courses);

        // When / Act
        var filteredCourses = business.retriveCourseRelatedToSpring("Leandro");

        // Then / Assert
        assertThat( filteredCourses.size(), is(4));
    }

    @DisplayName("Delete Courses not Related to Spring Using Mockito sould call Method")
    @Test
    void testDeleteCoursesNotRelatedToSpring_UsingMockitoVerify_Should_CallMethod_deleteCourse(){
        // Given / Arrange
        BDDMockito.given(mockService.retrieveCourses("Leandro")).willReturn(courses);
        String agileCourse = "Agile Desmistificado com Scrum, XP, Kanban e Trello";
        String spotifyCourse = "Spotify Engineering Culture Desmistificado";
        String microservicoCourse = "Microsserviços do 0 com Spring Cloud, Spring Boot e Docker";

        // When / Act
        business.deleteCourseNotRelatedToSpring("Leandro");
        // Then / Assert
        BDDMockito.verify(mockService).deleteCourse(agileCourse);
        BDDMockito.verify(mockService).deleteCourse(spotifyCourse);
        BDDMockito.verify(mockService, never()).deleteCourse(microservicoCourse);
    }

    @DisplayName("Delete Courses not Related to Spring Using Mockito sould call Method deleteCoursev2")
    @Test
    void testDeleteCoursesNotRelatedToSpring_UsingMockitoVerify_Should_CallMethod_deleteCoursev2(){
        // Given / Arrange
        BDDMockito.given(mockService.retrieveCourses("Leandro")).willReturn(courses);
        String agileCourse = "Agile Desmistificado com Scrum, XP, Kanban e Trello";
        String spotifyCourse = "Spotify Engineering Culture Desmistificado";
        String microservicoCourse = "Microsserviços do 0 com Spring Cloud, Spring Boot e Docker";
        // When / Act
        business.deleteCourseNotRelatedToSpring("Leandro");
        // Then / Assert
        BDDMockito.then(mockService).should().deleteCourse(agileCourse);
        BDDMockito.then(mockService).should().deleteCourse(spotifyCourse);
        BDDMockito.then(mockService).should(never()).deleteCourse(microservicoCourse);
    }

    @DisplayName("Delete Courses not Related to Spring Capturing Arguments sould call Method deleteCourse")
    @Test
    void testDeleteCoursesNotRelatedToSpring_CapturingArguments_Should_CallMethod_deleteCourse(){
        // Given / Arrange
        /*
        courses = Arrays.asList(
                "Agile Desmistificado com Scrum, XP, Kanban e Trello",
                "REST API's RESTFul do 0 à AWS com Spring Boot 3 Java e Docker"
        );*/

        BDDMockito.given(mockService.retrieveCourses("Leandro")).willReturn(courses);

        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);

        //String agileCourse = "Agile Desmistificado com Scrum, XP, Kanban e Trello";

        // When / Act
        business.deleteCourseNotRelatedToSpring("Leandro");
        // Then / Assert
        BDDMockito.then(mockService).should(times(7)).deleteCourse(argumentCaptor.capture());
        assertThat(argumentCaptor.getAllValues().size(),is(7));

    }

}