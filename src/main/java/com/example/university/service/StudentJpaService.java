package com.example.university.service;

import com.example.university.model.Student;
import com.example.university.model.Course;
import com.example.university.repository.StudentJpaRepository;
import com.example.university.repository.CourseJpaRepository;
import com.example.university.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CourseJpaService implements CourseRepository {

    @Autowired
    private StudentJpaRepository studentJpaRepository;

    @Autowired
    private CourseJpaRepository courseJpaRepository;

    public ArrayList<Course> getCourses() {
        List<Course> courseList = courseJpaRepository.findAll();
        ArrayList<Course> courses = new ArrayList<>(courseList);
        return courses;
    }

    public Course getCourseById(int courseId) {
        try {
            Course course = courseJpaRepository.findById(courseId).get();
            return course;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Course addCourse(Course course) {
        List<Integer> studentIds = new ArrayList<>();
        for (Student student : course.getStudents()) {
            studentIds.add(student.getStudentId());
        }

        List<Student> students = studentJpaRepository.findAllById(studentIds);
        course.setStudents(students);

        for (Student student : students) {
            student.getCourses().add(course);
        }

        Course savedCourse = courseJpaRepository.save(course);
        studentJpaRepository.saveAll(students);

        return savedCourse;
    }

    public Course updateCourse(int courseId, Course course) {
        try {
            Course newCourse = courseJpaRepository.findById(courseId).get();
            if (course.getCourseName() != null) {
                newCourse.setCourseName(course.getCourseName());
            }
            if (course.getCredits() != null) {
                newCourse.setCredits(course.getCredits());
            }
            if (course.getStudents() != null) {
                List<Student> students = newCourse.getStudents();
                for (Student student : students) {
                    student.getCourses().remove(newCourse);
                }
                studentJpaRepository.saveAll(students);
                List<Integer> newStudentIds = new ArrayList<>();
                for (Student student : course.getStudents()) {
                    newStudentIds.add(student.getStudentId());
                }
                List<Student> newStudents = studentJpaRepository.findAllById(newStudentIds);
                for (Student student : newStudents) {
                    student.getCourses().add(newCourse);
                }
                studentJpaRepository.saveAll(newStudents);
                newCourse.setStudents(newStudents);
            }
            return courseJpaRepository.save(newCourse);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public void deleteCourse(int courseId) {
        try {
            Course course = courseJpaRepository.findById(courseId).get();

            List<Student> students = course.getStudents();
            for (Student student : students) {
                student.getCourses().remove(course);
            }

            studentJpaRepository.saveAll(students);

            courseJpaRepository.deleteById(courseId);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    public List<Student> getCourseStudents(int courseId) {
        try {
            Course course = courseJpaRepository.findById(courseId).get();
            return course.getStudents();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}