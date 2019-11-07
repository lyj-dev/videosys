package com.lyj.dao;

import com.lyj.entity.Subject;

import java.util.List;

public interface SubjectDao {
    List<Subject> findAllSubjects();

    Subject query(int id);
}
