package com.smart.attendance.service;

import com.smart.attendance.entity.Student;
import com.smart.attendance.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student registerStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentByRollNumber(String rollNumber) {
        return studentRepository.findByRollNumber(rollNumber);
    }
}
