package br.com.farias.business;

import br.com.farias.service.CourseService;
import br.com.farias.service.stubs.CourseServiceStub;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CourseBusinessTest {

    @Test
    void testCourseRelatedToSpring_When_UsingAStub(){
        // Given / Arrange
        CourseService stubService = new CourseServiceStub();
        CourseBusiness business = new CourseBusiness(stubService);
        // When / Act
        var filteredCourses = business.retriveCourseRelatedToSpring("Leandro");
        // Then / Assert
        Assertions.assertEquals(4, filteredCourses.size());
    }

    @Test
    void testCourseRelatedToSpring_When_UsingAFooBarStudent(){
        // Given / Arrange
        CourseService stubService = new CourseServiceStub();
        CourseBusiness business = new CourseBusiness(stubService);
        // When / Act
        var filteredCourses = business.retriveCourseRelatedToSpring("Foo Bar");
        // Then / Assert
        Assertions.assertEquals(0, filteredCourses.size());
    }
}