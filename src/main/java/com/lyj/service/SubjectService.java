package com.lyj.service;

import com.lyj.entity.Subject;

import java.util.List;

public interface SubjectService {
    List<Subject> findAllSubjects();

    Subject query(int id);
}
