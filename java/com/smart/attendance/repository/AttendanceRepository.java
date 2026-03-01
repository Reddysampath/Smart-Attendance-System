package com.smart.attendance.repository;

import com.smart.attendance.entity.Attendance;
import com.smart.attendance.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByStudent(Student student);
    List<Attendance> findByTimestampBetween(LocalDateTime start, LocalDateTime end);
    
    @Query("SELECT a FROM Attendance a WHERE a.student.id = :studentId AND a.timestamp >= :start")
    List<Attendance> findRecentByStudent(Long studentId, LocalDateTime start);
}
