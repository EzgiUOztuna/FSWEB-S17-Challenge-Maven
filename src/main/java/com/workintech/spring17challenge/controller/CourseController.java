package com.workintech.spring17challenge.controller;

import com.workintech.spring17challenge.entity.HighCourseGpa;
import com.workintech.spring17challenge.entity.LowCourseGpa;
import com.workintech.spring17challenge.entity.MediumCourseGpa;
import com.workintech.spring17challenge.model.*;
import com.workintech.spring17challenge.validations.CourseValidation;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CourseController {
    private List<Course> courses;

    private CourseGpa lowCourseGpa;
    private CourseGpa mediumCourseGpa;
    private CourseGpa highCourseGpa;

    public CourseController(LowCourseGpa lowCourseGpa, MediumCourseGpa mediumCourseGpa, HighCourseGpa highCourseGpa){
        this.lowCourseGpa=lowCourseGpa;
        this.mediumCourseGpa=mediumCourseGpa;
        this.highCourseGpa=highCourseGpa;
    }

    @PostConstruct
    public void init(){
        courses = new ArrayList<>();
    }

    @GetMapping("/courses")
    public List<Course> getCourses(){
        return this.courses;
    }

    @GetMapping("/courses/{name}")
    public ResponseEntity<Course> getCoursesByName(@PathVariable String name){
        //CourseValidation.isValid(name);
        //CourseValidation.checkCourseExistence(courses, name, true);
        for(Course course: courses){
            if(course.getName().equalsIgnoreCase(name)){
                return ResponseEntity.ok(course);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping("/courses")
    public ResponseEntity<CourseResponse> saveCourse(@RequestBody Course course){
        //CourseValidation.checkCourseExistence(courses, course.getName(), false);
        courses.add(course);

        double totalGpa = 0;
        if(course.getCredit() <= 2){
            totalGpa = (course.getGrade().getCoefficient() * course.getCredit() * lowCourseGpa.getGpa());
        } else if (course.getCredit() == 3) {
            totalGpa = (course.getGrade().getCoefficient() * course.getCredit() * mediumCourseGpa.getGpa());
        } else if(course.getCredit() == 4){
            totalGpa = (course.getGrade().getCoefficient() * course.getCredit() * highCourseGpa.getGpa());
        }

        CourseResponse courseResponse = new CourseResponse(course, totalGpa);
        return new ResponseEntity<>(courseResponse, HttpStatus.CREATED);

    }

    @PutMapping("/courses/{id}")
    public CourseResponse updateCourse(@PathVariable int id, @RequestBody Course newCourse){
        //CourseValidation.isValidById(id);

        for(Course course: courses){
            if (course.getId().equals(id) ){
                course.setName(newCourse.getName());
                course.setCredit(newCourse.getCredit());
                course.setGrade(newCourse.getGrade());

                double totalGpa= course.getGrade().getCoefficient() * course.getCredit() *
                                (course.getCredit() <= 2 ? lowCourseGpa.getGpa()
                                : course.getCredit() == 3 ? mediumCourseGpa.getGpa()
                                : highCourseGpa.getGpa());

                return new CourseResponse(course, totalGpa);
            }
        }
        return null;
    }

    @DeleteMapping("/courses/{id}")
    public Course deleteCourse(@PathVariable int id){
        //CourseValidation.isValidById(id);
        //CourseValidation.checkCourseExistenceById(courses, id, true);
        for(Course course: courses){
            if(course.getId().equals(id) ){
                return course;
            }
        }
        return null;
    }
}
