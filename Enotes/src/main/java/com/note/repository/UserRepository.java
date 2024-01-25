package com.note.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.note.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {
//handle database operation
	public boolean existsByEmail(String email);
	
	public User findByEmail(String email);
}
