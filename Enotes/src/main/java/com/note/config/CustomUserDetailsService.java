package com.note.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.note.entity.User;
import com.note.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
//it interacts with the UserRepository to fetch user details from the 
	//database and converts them into a UserDetails object.
	
	@Autowired
	private UserRepository userRepo;	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepo.findByEmail(username);

		if (user == null) {
			throw new UsernameNotFoundException("user not found");
		} else {
			return new CustomUser(user);
		}

	}

}
