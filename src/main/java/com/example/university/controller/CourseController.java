package com.example.university.controller;

import com.example.university.model.Professor;
import com.example.university.model.Student;
import com.example.university.service.ProfessorJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class ProfessorController {
    @Autowired
    private ProfessorJpaService professorJpaService;

    @GetMapping("/courses/students/professors")
    public ArrayList<Professor> getProfessors() {
        return professorJpaService.getProfessors();
    }

    @GetMapping("/courses/students/professors/{professorId}")
    public Professor getProfessorById(@PathVariable("professorId") int professorId) {
        return professorJpaService.getProfessorById(professorId);
    }

    @PostMapping("/courses/students/professors")
    public Professor addProfessor(@RequestBody Professor professor) {
        return professorJpaService.addProfessor(professor);
    }

    @PutMapping("/courses/students/professors/{professorId}")
    public Professor updateProfessor(@PathVariable("professorId") int professorId, @RequestBody Professor professor) {
        return professorJpaService.updateProfessor(professorId, professor);
    }

    @DeleteMapping("/courses/students/professors/{professorId}")
    public void deleteProfessor(@PathVariable("professorId") int professorId) {
        professorJpaService.deleteProfessor(professorId);
    }

}