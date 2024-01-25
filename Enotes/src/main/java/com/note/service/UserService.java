package com.note.service;

import com.note.entity.User;

public interface UserService {
   public User saveUser(User user);
   
   public boolean existEmailCheck(String email);
}
