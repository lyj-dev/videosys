package com.lyj.entity;

import java.util.List;

public class Subject {
    private Integer id;
    private String name;

    private List<Course> courseList;

    public Subject() {}

    public Subject(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
