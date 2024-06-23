package com.LevelUp.LevelUp_FirstProject.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.LevelUp.LevelUp_FirstProject.Dto.UserDto;
import com.LevelUp.LevelUp_FirstProject.Entity.Users;
import com.LevelUp.LevelUp_FirstProject.Repositories.UserRepo;
import com.LevelUp.LevelUp_FirstProject.Service.UserService;
import com.LevelUp.LevelUp_FirstProject.Validator.ResourceNotFoundException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		Users users = this.dtoToUser(userDto);
		Users saveUsers = this.userRepo.save(users);
		return this.userToUserDto(saveUsers);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		Users users = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("Users", " id ", userId));
		
		users.setName(userDto.getName());
		users.setEmail(userDto.getEmail());
		users.setPassword(userDto.getPassword());
		users.setAbout(userDto.getAbout());
		
		Users updatUsers = this.userRepo.save(users);
		UserDto userDto1 = this.userToUserDto(updatUsers);
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		Users user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		return this.userToUserDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<Users> user = this.userRepo.findAll();
		List<UserDto> userDto = user.stream().map(users -> this.userToUserDto(users)).collect(Collectors.toList());
		return userDto;
	}

	@Override
	public void deleteUser(Integer userId) {
		Users user =this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		this.userRepo.delete(user);
	}

	public Users dtoToUser(UserDto userDto) {
		Users user = this.modelMapper.map(userDto, Users.class);
		return user;
//		Users users = new Users();
//		users.setId(userDto.getId());
//		users.setName(userDto.getName());
//		users.setEmail(userDto.getEmail());
//		users.setAbout(userDto.getAbout());
//		users.setPassword(userDto.getPassword());
//		return users;
	}

	public UserDto userToUserDto(Users user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		return userDto;
//		UserDto userDto = new UserDto();
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setAbout(user.getAbout());
//		userDto.setPassword(user.getPassword());
//		return userDto;
	}
}
