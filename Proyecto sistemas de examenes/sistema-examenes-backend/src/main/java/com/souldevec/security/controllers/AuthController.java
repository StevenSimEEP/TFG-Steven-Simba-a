package com.souldevec.security.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.souldevec.security.dtos.LoginUserDto;
import com.souldevec.security.dtos.NewUserDto;
import com.souldevec.security.services.AuthService;
import com.souldevec.security.services.UserService;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

	private final AuthService authService;
	private final UserService userService;

	@Autowired
	public AuthController(AuthService authService, UserService userService) {
		this.authService = authService;
		this.userService = userService;
	}

	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> login(@Valid @RequestBody LoginUserDto loginUserDto, BindingResult bindingResult) {
		 System.out.println("Usuario recibido: " + loginUserDto.getUserName());
		   System.out.println("Contraseña recibida: " + loginUserDto.getPassword());
		
		if (bindingResult.hasErrors()) {
	        return ResponseEntity.badRequest().body(Map.of("error", "Revise sus credenciales"));
	    }
	    try {
	        String jwt = authService.authenticate(loginUserDto.getUserName(), loginUserDto.getPassword());
	        return ResponseEntity.ok(Map.of("token", jwt));
	    } catch (Exception e) {
	        return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
	    }
	}

	@PostMapping("/register")
	public ResponseEntity<Map<String, String>> register(@Valid @RequestBody NewUserDto newUserDto,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			Map<String, String> errorResponse = new HashMap<>();
			errorResponse.put("error", "Revise los campos");
			return ResponseEntity.badRequest().body(errorResponse);
		}
		try {
			authService.registerUser(newUserDto);
			Map<String, String> response = new HashMap<>();
			response.put("message", "Usuario registrado exitosamente");
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} catch (IllegalArgumentException e) {
			Map<String, String> errorResponse = new HashMap<>();
			errorResponse.put("error", e.getMessage());
			return ResponseEntity.badRequest().body(errorResponse);
		}
	}

	@GetMapping("/check-auth")
	public ResponseEntity<String> checkAuth() {
		return ResponseEntity.ok().body("Autenticado");
	}
	
	@GetMapping("/actual-usuario")
	public ResponseEntity<?> obtenerUsuarioActual(@AuthenticationPrincipal UserDetails userDetails) {
	    if (userDetails == null) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Usuario no autenticado"));
	    }

	    Map<String, Object> userResponse = new HashMap<>();
	    userResponse.put("username", userDetails.getUsername());
	    userResponse.put("authorities", userDetails.getAuthorities());

	    return ResponseEntity.ok(userResponse);
	}
}
