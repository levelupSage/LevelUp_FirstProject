package com.LevelUp.LevelUp_FirstProject.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.LevelUp.LevelUp_FirstProject.Entity.Users;

public interface UserRepo extends JpaRepository<Users, Integer>{

	Optional<Users> findByEmail(String email);
}
