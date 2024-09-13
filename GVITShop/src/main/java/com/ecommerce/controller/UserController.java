package com.ecommerce.controller;

import com.ecommerce.dao.UserDao;
import com.ecommerce.model.User;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@SessionAttributes("User")
public class UserController {

    @Autowired
    private UserDao userDao;

    @GetMapping("/login")
    public ModelAndView showLoginPage() {
       return new ModelAndView("login");
    }
    
    @GetMapping("/signup")
    public ModelAndView showSignupPage() {
       return new ModelAndView("signup");
    }

    @RequestMapping("/register")
    @Transactional
    public ModelAndView register(@ModelAttribute User user){
        userDao.saveUser(user);
        ModelAndView mv = new ModelAndView("login");
        return mv;
    }

    @RequestMapping("/loginRequest")
    public ModelAndView login(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        User user = userDao.findByEmailAndPassword(email, password);  
        if (user != null) { 
            session.setAttribute("User", user); // Store user in session
            model.addAttribute("User", user); // Add user to model
            modelAndView.setViewName("redirect:/productList");
        } else {
            modelAndView.setViewName("login");
            modelAndView.addObject("error", "Invalid email or password");
        }
        return modelAndView;
    }
    
    @RequestMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) { 
        session.invalidate();
        redirectAttributes.addFlashAttribute("success", "You have been  logged out successfully.");
        return "redirect:/login";
    }
    
    @GetMapping("/profile")
    public String getProfile(@ModelAttribute("User") User user, Model model) {
        if (user != null) {
            User profile = userDao.userDetails(user.getEmail()); // Fetch user details
            model.addAttribute("profile", profile);
            return "profile"; // Return profile view
        } else {
            return "redirect:/login"; // Redirect to login if user is not found in session
        }
    }

}
