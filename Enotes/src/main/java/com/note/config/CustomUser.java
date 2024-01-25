package com.note.config;

import java.util.Arrays;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.note.entity.User;

/*The CustomUser class implements the UserDetails interface, which is a core interface in Spring Security 
  for representing user details. */
public class CustomUser implements UserDetails {

	private User user;
	
	
	public CustomUser(User user) {
		super();
		this.user = user;
	}

/* methods such as getAuthorities(), getPassword(), getUsername(), etc., 
   need to be implemented with the actual logic to provide user details. */	
 
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority=new SimpleGrantedAuthority(user.getRole());
		return Arrays.asList(authority);
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

		@Override
	public String getUsername() {
		// here we take username as email so 
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {	
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
