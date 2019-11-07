package com.lyj.service.impl;

import com.github.pagehelper.PageHelper;
import com.lyj.dao.CourseDao;
import com.lyj.entity.Course;
import com.lyj.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseDao courseDao;
    @Override
    public List<Course> findAllCourse(Map<String, Integer> pageMap) {
        // 设置页码和每页显示的记录数，该语句后面，紧跟着数据库查询相关的语句
        if (pageMap != null) {
            PageHelper.startPage(pageMap.get("page"), pageMap.get("limit"));
        }
        return courseDao.findAllCourses();
    }

    @Override
    public Course selectCourse(String id) {
        return courseDao.selectCourseById(Integer.valueOf(id));
    }

    @Override
    public void addCourse(Course course) {
        courseDao.addCourse(course);
    }

    @Override
    public void updateCourse(Course course) {
        courseDao.updateCourse(course);
    }

    @Override
    public void delete(int[] id) {
        courseDao.delete(id);
    }
}
