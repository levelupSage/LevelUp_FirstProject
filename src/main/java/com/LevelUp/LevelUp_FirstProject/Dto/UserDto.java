package com.LevelUp.LevelUp_FirstProject.Dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private Integer id;
	
	@NotBlank(message = "First Enter Your Name.")
	@Size(min=4, max=10, message = "User Name must be minimum of 10 character")
	private String name;
	
	@Email
	@NotEmpty(message = "Enter Your Valid Email.")
	private String email;
	
	@NotEmpty(message = "Password is medentory")
	@Size(min=6, max=12, message = "Password must be minimum of 6 character")
	private String password;
	
	@NotEmpty(message = "About is not Empty")
	private String about;
}

