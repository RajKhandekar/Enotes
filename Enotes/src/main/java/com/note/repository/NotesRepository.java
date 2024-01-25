package com.note.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.note.entity.Notes;
import com.note.entity.User;

public interface NotesRepository extends JpaRepository<Notes,Integer> {

	public 	List<Notes> findByUser(User user); 
}
