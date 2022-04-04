package com.vti.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class SigninDTO {
	@NotBlank(message = "Username can not be null")
	private String username;
	
	@NotBlank(message = "Username can not be null")
	private String password;
}
