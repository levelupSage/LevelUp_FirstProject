package com.LevelUp.LevelUp_FirstProject.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.LevelUp.LevelUp_FirstProject.Dto.ResponseApi;
import com.LevelUp.LevelUp_FirstProject.Dto.UserDto;
import com.LevelUp.LevelUp_FirstProject.Service.UserService;


@RestController
@RequestMapping("api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	//GET
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		return ResponseEntity.ok(this.userService.getAllUsers());
	}
	
	//POST-create user
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		UserDto createUserDto = this.userService.createUser(userDto);
		return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
	}
	
	//PUT
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Integer id){
		UserDto user = this.userService.updateUser(userDto, id);
		return ResponseEntity.ok(user);
	}
	
	//DELETE - Delete User
	@DeleteMapping("/{userId}")
	public ResponseEntity<ResponseApi> deleteUser(@PathVariable("userId") Integer uid){
		this.userService.deleteUser(uid);
		return new ResponseEntity<>(new ResponseApi("User Deleted Successfully", true), HttpStatus.OK);
		//return new ResponseEntity(Map.of("message", "User Delete Successfully"), HttpStatus.OK);
	}
	
	//GET
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId){
		return ResponseEntity.ok(this.userService.getUserById(userId));
	}
}
