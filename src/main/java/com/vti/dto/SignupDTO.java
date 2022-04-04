package com.vti.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class SignupDTO {
	@NotNull(message = "Username can not be null")
	@NotBlank(message = "Username can not be blank")
	private String username;
	
	private String firstName;
	
	private String lastName;
	
	@Email(message = "Malformed email")
	private String email;
	
	@Length(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
	private String password;
}
