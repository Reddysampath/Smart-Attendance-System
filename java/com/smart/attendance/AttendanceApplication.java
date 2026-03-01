package com.smart.attendance;

import com.smart.attendance.entity.Admin;
import com.smart.attendance.repository.AdminRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class AttendanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AttendanceApplication.class, args);
	}

	@Bean
	CommandLineRunner init(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			if (adminRepository.findByUsername("admin").isEmpty()) {
				Admin admin = Admin.builder()
						.username("admin")
						.password(passwordEncoder.encode("admin123"))
						.role("ROLE_ADMIN")
						.build();
				adminRepository.save(admin);
				System.out.println("Default admin user created: admin / admin123");
			}
		};
	}
}
