package com.lyj.service;

import com.lyj.entity.Course;

import java.util.List;
import java.util.Map;

public interface CourseService {
    List<Course> findAllCourse(Map<String, Integer> pageMap);

    Course selectCourse(String id);

    void addCourse(Course course);

    void updateCourse(Course course);

    void delete(int[] id);
}
