package com.alpa.test;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class School {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @OneToMany(
        mappedBy = "school"
    )
    private List<Student> students;
    

    public List<Student> getStudents() {
        return students;
    }



    public void setStudents(List<Student> students) {
        this.students = students;
    }



    public School(String name) {
        this.name = name;
    }



    public Integer getId() {
        return id;
    }
    public void setId() {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName() {
        this.name = name;
    }

}
