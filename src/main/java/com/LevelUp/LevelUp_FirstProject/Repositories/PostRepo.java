package com.LevelUp.LevelUp_FirstProject.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.LevelUp.LevelUp_FirstProject.Entity.Category;
import com.LevelUp.LevelUp_FirstProject.Entity.Post;
import com.LevelUp.LevelUp_FirstProject.Entity.Users;


public interface PostRepo extends JpaRepository<Post, Integer>{

	List<Post> findByUsers(Users users);
	List<Post> findByCategory(Category category);
	
	@Query("select p from Post p where p.title like :key")
	List<Post> searchById(@Param("key") String title);
	//List<Post> findByTitleContaining(String title);
}
