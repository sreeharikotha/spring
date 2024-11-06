package com.example.university.repository;

import com.example.university.model.Professor;
import com.example.university.model.Student;

import java.util.ArrayList;
import java.util.List;

public interface ProfessorRepository {
    ArrayList<Professor> getProfessors();

    Professor getProfessorById(int professorId);

    Professor addProfessor(Professor professor);

    Professor updateProfessor(int professorId, Professor professor);

    void deleteProfessor(int professorId);

    Student getProfessorStudent(int professorId);
}
