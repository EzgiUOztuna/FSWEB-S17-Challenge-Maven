package com.workintech.spring17challenge.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Course {
    private String name;
    private int credit;
    private Grade grade;
    private int id;

}
