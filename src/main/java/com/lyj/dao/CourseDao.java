package com.lyj.dao;

import com.lyj.entity.Course;

import java.util.List;

public interface CourseDao {
    List<Course> findAllCourses();

    Course selectCourseById(Integer valueOf);

    void updateCourse(Course course);

    void addCourse(Course course);

    void delete(int[] id);
}
