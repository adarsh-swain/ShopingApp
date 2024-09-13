package com.ecommerce.daoImpl;

import com.ecommerce.dao.UserDao;
import com.ecommerce.model.User;
import com.ecommerce.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttributes;

@Service
@SessionAttributes("loginUserbean")
public class UserDaoImpl implements UserDao {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private HttpSession session;

    @Override
    public String saveUser(User user) {
    userRepository.save(user);
       return "Register SuccessFully";
    }

    public User findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    
    public User getCurrentUser() {
        return (User) session.getAttribute("currentUser");
    }

    @Override
    public User userDetails(String email) {
        // Use the UserRepository to find the user by email (or any unique identifier like ID)
        return userRepository.findByEmail(email)
                             .orElseThrow(() -> new RuntimeException("User not found!"));
    }

}
