package com.smart.attendance.controller;

import com.smart.attendance.entity.Attendance;
import com.smart.attendance.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/mark")
    public ResponseEntity<Attendance> markAttendance(@RequestBody Map<String, Object> payload) {
        Long studentId = Long.valueOf(payload.get("studentId").toString());
        String status = (String) payload.get("status");
        return ResponseEntity.ok(attendanceService.markAttendance(studentId, status));
    }

    @GetMapping("/daily")
    public ResponseEntity<List<Attendance>> getDailyAttendance(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(attendanceService.getDailyAttendance(date));
    }

    @GetMapping("/monthly")
    public ResponseEntity<List<Attendance>> getMonthlyAttendance(
            @RequestParam int year, @RequestParam int month) {
        return ResponseEntity.ok(attendanceService.getMonthlyReport(year, month));
    }
}
