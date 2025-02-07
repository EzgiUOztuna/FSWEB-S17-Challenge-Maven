package com.workintech.spring17challenge.model;

public class LowCourseGpa implements CourseGpa{
    @Override
    public int getGpa() {
        return 3;
    }
}
