package com.smart.attendance.controller;

import com.smart.attendance.entity.Student;
import com.smart.attendance.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/register")
    public ResponseEntity<Student> registerStudent(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.registerStudent(student));
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }
}
