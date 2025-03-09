package com.sistemas_examenes;

import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sistemas_examenes.entities.Role;
import com.sistemas_examenes.entities.User;
import com.sistemas_examenes.enums.RoleList;
import com.sistemas_examenes.repositories.RoleRepository;
import com.sistemas_examenes.repositories.UserRepository;

@SpringBootApplication
public class SecurityApplication implements CommandLineRunner{

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;

	public SecurityApplication(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
	        this.userRepository = userRepository;
	        this.roleRepository = roleRepository;
	        this.passwordEncoder = passwordEncoder;
	    }

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}

	public void run(String... args) {
		if (!userRepository.existsByUserName("admin")) {
			System.out.println("⚡ Creando usuario admin...");

			Optional<Role> adminRoleOpt = roleRepository.findByName(RoleList.ADMIN);
			Role adminRole = adminRoleOpt.orElseGet(() -> {
				Role newRole = new Role();
				newRole.setName(RoleList.ADMIN);
				return roleRepository.save(newRole);
			});

			User admin = new User();
			admin.setUserName("admin");
			admin.setPassword(passwordEncoder.encode("12345")); // Cambia la contraseña si quieres
			admin.setNombre("Administrador");
			admin.setApellido("Sistema");
			admin.setEmail("admin@example.com");
			admin.setTelefono("123456789");
			admin.setRole(adminRole); // Asignar el rol de ADMIN

			userRepository.save(admin);
			System.out.println("✅ Usuario admin creado con éxito.");
		} else {
			System.out.println("ℹ️ Usuario admin ya existe, no se creó uno nuevo.");
		}
	}

}
