package com.LevelUp.LevelUp_FirstProject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.LevelUp.LevelUp_FirstProject.Entity.Users;
import com.LevelUp.LevelUp_FirstProject.Repositories.UserRepo;
import com.LevelUp.LevelUp_FirstProject.Validator.ResourceNotFoundException;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		// Loading user from database by username
		Users user = this.userRepo.findByEmail(username)
				.orElseThrow(() -> new ResourceNotFoundException("Users ", "Email : " + username, 0));
		return user;
	}

}
