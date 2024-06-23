package com.LevelUp.LevelUp_FirstProject.Dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

	private Integer categoryId;
	
	@NotBlank
	@Size(min=6, max=20, message = "category title is not empty")
	private String categoryTitle;
	
	@NotBlank
	@Size(max=100, message = "category discreption is not empty")
	private String categoryDisc;
}
