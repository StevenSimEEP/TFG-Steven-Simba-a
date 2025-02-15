package com.souldevec.security.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserDto {

    public String userName;
    public String password;
	public String getUserName() {
		return userName;
	}
	public String getPassword() {
		return password;
	}
}
