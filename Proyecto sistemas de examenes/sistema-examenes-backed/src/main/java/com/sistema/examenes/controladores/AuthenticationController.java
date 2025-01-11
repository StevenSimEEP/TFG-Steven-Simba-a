package com.sistema.examenes.controladores;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.examenes.configuraciones.JwtUtils;
import com.sistema.examenes.entidades.JwtRequest;
import com.sistema.examenes.entidades.JwtResponse;
import com.sistema.examenes.entidades.Usuario;
import com.sistema.examenes.excepciones.UsuarioNotFoundException;
import com.sistema.examenes.servicios.impl.UserDetailsServiceImpl;

@RestController
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private JwtUtils jwtUtils;

	@PostMapping("/generate-token")
	@CrossOrigin("*")
	public ResponseEntity<?> generarToken(@RequestBody JwtRequest jwtRequest) throws Exception {
	    System.out.println("Solicitud de generación de token para usuario: " + jwtRequest.getUsername());

		try {
			autenticar(jwtRequest.getUsername(), jwtRequest.getPassword());
	        System.out.println("Autenticación exitosa");
		} catch (UsuarioNotFoundException exception) {
			exception.printStackTrace();
			throw new Exception("Usuario no encotrado");
		} catch (Exception e) {
	        System.out.println("Error durante la autenticación: " + e.getMessage());
	        throw e;
	    }
		
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
	    System.out.println("Cargando detalles del usuario: " + userDetails.getUsername());

		String token = this.jwtUtils.generateToken(userDetails);
	    System.out.println("Token generado: " + token);

		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	private void autenticar(String username, String password) throws Exception{
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException disabledException) {
			throw new Exception("USUARIO DESHABILITADO " + disabledException.getMessage());
		} catch (BadCredentialsException badCredentialsException) {
			throw new Exception("Credenciales inválidas" + badCredentialsException.getMessage());	
		}
	}
	
	@GetMapping("/actual-usuario")
	public Usuario obtenerUsuarioActual(Principal principal) {
		return (Usuario) this.userDetailsService.loadUserByUsername(principal.getName());
	}
	
}
