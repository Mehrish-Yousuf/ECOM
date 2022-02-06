package com.ecom.user.service;

import com.ecom.user.DTO.UserDTO;
import com.ecom.user.entity.User;

import java.util.List;


public interface UserService {
    List<User> getAllUsers();
    UserDTO getUserById(Long id);
    User getUserByName(String userName);
    User saveUser(User user);
    User updateUser(User user);
}
