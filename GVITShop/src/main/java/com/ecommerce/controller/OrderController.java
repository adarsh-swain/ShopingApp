package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ecommerce.dao.OrderDao;
import com.ecommerce.model.AddressForm;
import com.ecommerce.model.User;

import jakarta.servlet.http.HttpSession;

@Controller
@SessionAttributes("User")
public class OrderController {
	
	@Autowired
	private OrderDao orderDao;
	
	@GetMapping("/buy-now")
    public String showAddressForm(@RequestParam("productId") Long productId, Model model, HttpSession session) {
        User user = (User) session.getAttribute("User");

        if (user == null) {
            return "redirect:/login"; // Redirect to login page if not logged in
        }

        // Add productId and an empty AddressForm to the model
        model.addAttribute("productId", productId);
        model.addAttribute("addressForm", new AddressForm());
        model.addAttribute("quantity", 1); // Default quantity
        return "address";
    }

    @RequestMapping("/proceed-to-checkout")
    public String proceedToCheckout(@ModelAttribute("addressForm") AddressForm addressForm,
                                    @RequestParam("productId") Long productId,
                                    @RequestParam("quantity") int quantity,
                                    HttpSession session,
                                    RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("User");
        if (user == null) {
            redirectAttributes.addFlashAttribute("error", "You need to log in to proceed.");
            return "redirect:/login"; 
        }
        if (quantity <= 0) {
            redirectAttributes.addFlashAttribute("error", "Invalid quantity.");
            return "redirect:/buy-now?productId=" + productId;
        }
        boolean success = orderDao.createOrder(user, productId, quantity, addressForm);
        if (success) {
            redirectAttributes.addFlashAttribute("message", "Order placed successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Order failed. Please try again.");
        }
        return "redirect:/order-confirmation";
    }

}
