package com.workintech.spring17challenge.validations;

import com.workintech.spring17challenge.exceptions.ApiException;
import com.workintech.spring17challenge.model.Course;
import org.springframework.http.HttpStatus;

import java.util.List;

public class CourseValidation {
    public static void isValid(String name){
        if(name == null || name.trim().isEmpty()){
            throw new ApiException("Name cannot find: " + name, HttpStatus.NOT_FOUND);
        }
    }

    public static void checkCourseExistence(List<Course> courses, String name, boolean existence){
        for(Course course : courses){
            if(course.getName().equals(name)){
                if(!existence){
                    throw new ApiException("Course is already exist: " + name, HttpStatus.BAD_REQUEST);
                }
                return; // Eğer kurs bulunduysa, işlem tamamlanmış demektir
            }
        }

        if(existence){
            throw new ApiException("Course cannot find: " + name, HttpStatus.NOT_FOUND);
        }
    }


    public static void isValidById(Integer id){
        if(id == null || id<0){
            throw new ApiException("Id is not valid: " + id, HttpStatus.BAD_REQUEST);
        }
    }

    public static void checkCourseExistenceById(List<Course> courses, int id, boolean existence){
        for(Course course: courses){
            if(course.getId().equals(id)){
                if(existence){
                    throw new ApiException("Course is already exist: " + id, HttpStatus.BAD_REQUEST);
                } else {
                    throw new ApiException("Course cannot find: " + id, HttpStatus.NOT_FOUND);
                }
            }
        }
    }



}
