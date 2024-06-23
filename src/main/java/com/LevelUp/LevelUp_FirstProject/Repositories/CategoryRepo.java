package com.LevelUp.LevelUp_FirstProject.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.LevelUp.LevelUp_FirstProject.Entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
