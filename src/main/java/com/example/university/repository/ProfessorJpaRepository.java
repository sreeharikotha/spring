package com.example.university.repository;

import com.example.university.model.Professor;
import com.example.university.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessorJpaRepository extends JpaRepository<Professor, Integer> {
    List<Professor> findByStudent(Student student);
}