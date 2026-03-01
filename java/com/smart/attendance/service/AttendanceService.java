package com.smart.attendance.service;

import com.smart.attendance.entity.Attendance;
import com.smart.attendance.entity.Student;
import com.smart.attendance.repository.AttendanceRepository;
import com.smart.attendance.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private StudentRepository studentRepository;

    public Attendance markAttendance(Long studentId, String status) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        // Check if attendance already marked for today
        LocalDateTime startOfDay = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        List<Attendance> todayAttendance = attendanceRepository.findRecentByStudent(studentId, startOfDay);

        if (!todayAttendance.isEmpty()) {
            return todayAttendance.get(0); // Return existing
        }

        Attendance attendance = Attendance.builder()
                .student(student)
                .status(status != null ? status : "PRESENT")
                .build();

        return attendanceRepository.save(attendance);
    }

    public List<Attendance> getDailyAttendance(LocalDate date) {
        LocalDateTime start = LocalDateTime.of(date, LocalTime.MIN);
        LocalDateTime end = LocalDateTime.of(date, LocalTime.MAX);
        return attendanceRepository.findByTimestampBetween(start, end);
    }

    public List<Attendance> getMonthlyReport(int year, int month) {
        LocalDate startMonth = LocalDate.of(year, month, 1);
        LocalDateTime start = LocalDateTime.of(startMonth, LocalTime.MIN);
        LocalDateTime end = LocalDateTime.of(startMonth.withDayOfMonth(startMonth.lengthOfMonth()), LocalTime.MAX);
        return attendanceRepository.findByTimestampBetween(start, end);
    }
}
