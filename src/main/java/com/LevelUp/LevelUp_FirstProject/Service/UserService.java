package com.LevelUp.LevelUp_FirstProject.Service;

import java.util.List;

import com.LevelUp.LevelUp_FirstProject.Dto.UserDto;

public interface UserService {

	UserDto createUser(UserDto user);

	UserDto updateUser(UserDto user, Integer userId);

	UserDto getUserById(Integer userId);

	List<UserDto> getAllUsers();

	void deleteUser(Integer userId);
}
