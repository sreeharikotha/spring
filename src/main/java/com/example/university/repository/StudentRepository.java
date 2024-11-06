package com.example.university.repository;

import java.util.ArrayList;
import java.util.List;

import com.example.university.model.Student;
import com.example.university.model.Course;

public interface CourseRepository {
    ArrayList<Course> getCourses();

    Course getCoursesById(int coursesId);

    Course addCourse(Course course);

    Course updateCourse(int courseId, Course course);

    void deleteCourse(int courseId);

    List<Student> getCourseStudents(int courseId);
}
