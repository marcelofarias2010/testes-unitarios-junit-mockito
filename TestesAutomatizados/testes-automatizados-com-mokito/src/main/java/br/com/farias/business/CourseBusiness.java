package br.com.farias.business;

import br.com.farias.service.CourseService;

import java.util.ArrayList;
import java.util.List;

// System (method) Under Tes
public class CourseBusiness {

    // CourseService is a dependency
    private CourseService service;

    public CourseBusiness(CourseService service) {
        this.service = service;
    }

    public List<String> retriveCourseRelatedToSpring(String student){
        var filteredCourse = new ArrayList<String>();
        if("Foo Bar".equals(student)) return filteredCourse;
        var allCourses = service.retrieveCourses(student);

        for(String course : allCourses){
            if (course.contains("Spring")) {
                filteredCourse.add(course);
            }
        }

        return filteredCourse;
    }

    public void deleteCourseNotRelatedToSpring(String student){

        var allCourses = service.retrieveCourses(student);

        for(String course : allCourses){
            if (!course.contains("Spring")) {
                service.deleteCourse(course);
            }
        }
    }
}
