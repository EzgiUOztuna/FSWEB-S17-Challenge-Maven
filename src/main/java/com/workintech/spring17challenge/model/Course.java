package com.workintech.spring17challenge.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Course {
    @NotNull
    private String name;
    @NotNull
    private Integer credit;
    @NotNull
    private Grade grade;
    @NotNull
    private Integer id;

}
