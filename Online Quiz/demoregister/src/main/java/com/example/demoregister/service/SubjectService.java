package com.example.demoregister.service;

import com.example.demoregister.entity.Subject;
import com.example.demoregister.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {
    @Autowired
    public SubjectRepository repository;
    public Subject saveSubject(Subject subject){return repository.save(subject);}
    public Subject getSubjectById(Long subjectId) {
        return repository.getById(subjectId);
    }
    public List<Subject> getAllSubject() {
        return repository.findAll();
    }
}
