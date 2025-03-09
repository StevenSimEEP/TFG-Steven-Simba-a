package com.sistemas_examenes.initializers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import com.sistemas_examenes.entities.Role;
import com.sistemas_examenes.enums.RoleList;
import com.sistemas_examenes.repositories.RoleRepository;

public class RoleInitializer {
	
	@Bean
    public CommandLineRunner initRoles(RoleRepository roleRepository) {
        return args -> {
            if (roleRepository.findByName(RoleList.NORMAL).isEmpty()) {
            	roleRepository.save(new Role(RoleList.NORMAL));
            }
            if (roleRepository.findByName(RoleList.ADMIN).isEmpty()) {
                roleRepository.save(new Role(RoleList.ADMIN));
            }
        };
    }
}
