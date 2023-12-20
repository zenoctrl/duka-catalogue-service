package com.example.dukacatalogue.service;

import com.example.dukacatalogue.entity.user.User;

import java.util.List;

public interface DukaUserService {
   public List<User> getAllCustomers();

   public User getUserById(String id) throws Exception;

  public User save(User user) ;

    public User update(String id, User user) throws Exception;

    public String delete(String id) throws Exception;
}
