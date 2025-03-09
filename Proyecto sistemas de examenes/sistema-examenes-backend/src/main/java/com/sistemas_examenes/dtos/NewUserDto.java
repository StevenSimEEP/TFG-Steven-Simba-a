package com.sistemas_examenes.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewUserDto {
	
    public String userName;
    public String password;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
	
	public String getUserName() {
		return userName;
	}
	public String getPassword() {
		return password;
	}
    
    public String getNombre() {
		return nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public String getEmail() {
		return email;
	}
	public String getTelefono() {
		return telefono;
	}
}
