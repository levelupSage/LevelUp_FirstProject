package com.LevelUp.LevelUp_FirstProject.Service;

import java.util.List;

import com.LevelUp.LevelUp_FirstProject.Dto.CategoryDto;

public interface CategoryService {

	//Create
	CategoryDto createCategroy(CategoryDto categoryDto);
	
	//update
	CategoryDto updateCategory(CategoryDto categoryDto, Integer id);
	
	//delete
	void deleteCatgory(Integer categoryId);
	
	//get
	CategoryDto getCategory(Integer categoryId);
	
	//getAll
	List<CategoryDto> getCategories();
}

