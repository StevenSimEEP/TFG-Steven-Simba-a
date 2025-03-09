package com.sistemas_examenes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sistemas_examenes.dtos.NewUserDto;
import com.sistemas_examenes.entities.Role;
import com.sistemas_examenes.entities.User;
import com.sistemas_examenes.enums.RoleList;
import com.sistemas_examenes.jwt.JwtUtil;
import com.sistemas_examenes.repositories.RoleRepository;

@Service
public class AuthService {

    private final UserService userService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @Autowired
    public AuthService(UserService userService, RoleRepository roleRepository, PasswordEncoder passwordEncoder, 
    			JwtUtil jwtUtil, AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }

    public String authenticate(String username, String password){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,password);
        Authentication authResult = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authResult);
        return jwtUtil.generateToken(authResult);
    }

    public void registerUser(NewUserDto newUserDto){
        if (userService.existsByUserName(newUserDto.getUserName())){
            throw new IllegalArgumentException("El nombre de usuario ya existe");
        }

        System.out.println("userName recibido: " + newUserDto.getUserName());

        if (newUserDto.getUserName() == null || newUserDto.getUserName().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de usuario no puede estar vacío");
        }
        
        Role roleUser = roleRepository.findByName(RoleList.NORMAL)
        	    .orElseGet(() -> roleRepository.save(new Role(RoleList.NORMAL)));
        
        User user = new User(
        		newUserDto.getUserName(), 
        		passwordEncoder.encode(
        				newUserDto.getPassword()), 
        				newUserDto.getNombre(), 
        				newUserDto.getApellido(), 
        				newUserDto.getEmail(), 
        				newUserDto.getTelefono(), 
        				roleUser);
        userService.save(user);
    }
}
