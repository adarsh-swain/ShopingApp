package com.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ecommerce.dao.CartItemDao;
import com.ecommerce.dao.UserDao;
import com.ecommerce.model.CartItem;
import com.ecommerce.model.User;

import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;

@Controller
@SessionAttributes("User")
public class CartItemController {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private CartItemDao cartItemDao;
	
	@RequestMapping("/add-to-cart")
    public String addToCart(
            @ModelAttribute("User") User user,
            @RequestParam("productId") Long productId,
            @RequestParam("quantity") Integer quantity,
            RedirectAttributes redirectAttributes) {

        // Check if user is not in session
        if (user == null || user.getUserid() == null) {
            redirectAttributes.addFlashAttribute("error", "You need to log in to add items to your cart.");
            return "redirect:/login";
        }

        try {
            cartItemDao.addToCart(user, productId, quantity); // Add the item to the cart
            redirectAttributes.addFlashAttribute("success", "Item added to cart successfully.");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/productList";
    }

	@GetMapping("/cartsOfUser")
	public String viewCart(@ModelAttribute("User") User user, Model model, HttpSession session, RedirectAttributes redirectAttributes) { 
	    if (user == null || user.getUserid() == null) {
	        redirectAttributes.addFlashAttribute("error", "Please log in to view your cart.");
	        return "redirect:/login";
	    }
	    List<CartItem> cartItems = cartItemDao.getUserCart(user);
	    model.addAttribute("cartItems", cartItems);
	    return "cart";
	}
	
	@GetMapping("/remove-from-cart")
	public String removeFromCart(HttpSession session, @RequestParam("itemId") Long itemId, RedirectAttributes redirectAttributes) {
	    User user = (User) session.getAttribute("user");
	    if (user == null || itemId == null) {
	        redirectAttributes.addFlashAttribute("error", "Invalid request.");
	        return "redirect:/cartsOfUser";
	    }
	    try {
	        cartItemDao.removeFromCart(itemId);
	        redirectAttributes.addFlashAttribute("success", "Item removed from cart successfully.");
	    } catch (Exception e) {
	        redirectAttributes.addFlashAttribute("error", "Failed to remove item from cart.");
	    }
	    return "redirect:/cartsOfUser"; // Redirect back to the cart page
	}

}
