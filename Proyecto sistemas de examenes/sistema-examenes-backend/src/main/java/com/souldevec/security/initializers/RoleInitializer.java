package com.souldevec.security.initializers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import com.souldevec.security.entities.Role;
import com.souldevec.security.enums.RoleList;
import com.souldevec.security.repositories.RoleRepository;

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
