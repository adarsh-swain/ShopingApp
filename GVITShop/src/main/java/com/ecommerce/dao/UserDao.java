package com.ecommerce.dao;

import com.ecommerce.model.User;

public interface UserDao {
    public String saveUser(User user);
    public User findByEmailAndPassword(String email, String password);
    public User getCurrentUser();
    public User userDetails(String email);
}
