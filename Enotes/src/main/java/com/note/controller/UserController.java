package com.note.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.note.repository.UserRepository;
import com.note.service.NotesService;

import jakarta.servlet.http.HttpSession;

import com.note.entity.*;
@Controller
@RequestMapping("/user")
//supppose user login successfully then only this method are call otherwise not
public class UserController {
	
	@Autowired 
	private UserRepository userRepo;
	
	@Autowired 
	private NotesService notesService;

	//this method is use for setting user email when he login
	@ModelAttribute //this is call when user successfully login and then we give name dynamically to login button
	                  // which is name of user which login here             
	public User getUser(Principal p,Model m) {
		String email=p.getName();
		User user=userRepo.findByEmail(email);
		m.addAttribute("user",user);
		return user;
	}
	
	@GetMapping("/addNotes")
	public String add_notes() {
		return "add_notes";
	}

	@GetMapping("/viewNotes")
	public String viewNotes(Model m,Principal p) {
		User user=getUser(p,m);
		List <Notes> notes=notesService.getNotesByUser(user);
		m.addAttribute("notesList",notes);
		notesService.getNotesByUser(user);
		return "view_notes";
	}

	@GetMapping("/editNotes/{id}")
	public String editNotes(@PathVariable int id,Model m) {
		Notes notes=notesService.getNotesById(id);
		m.addAttribute("n",notes);
		return "edit_notes";
	}
	
	@PostMapping("/saveNotes")
	public String saveNotes(@ModelAttribute Notes notes,HttpSession session,Principal p,Model m) {
		notes.setDate(LocalDate.now());
		notes.setUser(getUser(p,m));
		
		Notes saveNotes=notesService.saveNotes(notes);
		if(saveNotes!=null) {
			session.setAttribute("msg", "Notes save Success");
		}
		else {
			session.setAttribute("msg", "Something wrong on server");
		}
		return "redirect:/user/viewNotes";
	}
	
	@PostMapping("/updateNotes")
	public String updateNotes(@ModelAttribute Notes notes,HttpSession session,Principal p,Model m) {
		notes.setDate(LocalDate.now());
		notes.setUser(getUser(p,m));
		
		Notes saveNotes=notesService.saveNotes(notes);
		if(saveNotes!=null) {
			session.setAttribute("msg", "Notes update Success");
		}
		else {
			session.setAttribute("msg", "Something wrong on server");
		}
		return "redirect:/user/viewNotes";
	}
	
	@GetMapping("/deleteNotes/{id}")
	public String deleteNotes(@PathVariable int id,HttpSession session) {
		boolean f=notesService.deleteNotes(id);
		if(f) {
			session.setAttribute("msg","Delete success");
		}
		else {
			session.setAttribute("msg","something wrong on server");
		}
		
		return "redirect:/user/viewNotes";
	}
	
}
