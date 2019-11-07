package com.lyj.service.impl;

import com.lyj.dao.SubjectDao;
import com.lyj.entity.Subject;
import com.lyj.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    SubjectDao subjectDao;


    @Override
    public List<Subject> findAllSubjects() {
        return subjectDao.findAllSubjects();
    }

    @Override
    public Subject query(int id) {
        return subjectDao.query(id);
    }
}
