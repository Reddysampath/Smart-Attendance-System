package com.smart.attendance.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String rollNumber;

    @Column(nullable = false)
    private String department;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String faceDescriptor; // JSON string of 128-float array

    private LocalDateTime registeredAt;

    @PrePersist
    protected void onCreate() {
        registeredAt = LocalDateTime.now();
    }
}
