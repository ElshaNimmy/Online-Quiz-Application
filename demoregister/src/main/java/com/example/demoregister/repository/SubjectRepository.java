package com.example.demoregister.repository;

import com.example.demoregister.entity.QuizList;
import com.example.demoregister.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SubjectRepository extends JpaRepository<Subject,Long> {

}
