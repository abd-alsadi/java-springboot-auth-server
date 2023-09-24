package com.core.authserver.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.core.authserver.models.*;
import com.core.authserver.models.ui.CustomUserDetails;
import com.core.authserver.repositories.*;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserModel user=userRepository.findByUserName(username);
		System.out.println("inside user details Service");
		if(user==null) {
			throw new UsernameNotFoundException(username+" not found");
		}
		
		return new CustomUserDetails(user);
	}

}
