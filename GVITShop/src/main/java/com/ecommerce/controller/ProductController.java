package com.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import com.ecommerce.dao.ProductDao;
import com.ecommerce.model.Product;
import com.ecommerce.model.User;

import jakarta.servlet.ServletContext;

@Controller
@SessionAttributes("User")
public class ProductController implements ServletContextAware{
	
	@Autowired
	private ProductDao productDao;
	
	@Override
	public void setServletContext(ServletContext servletContext) {
	}
	
//	@GetMapping("/productList")
//    public ModelAndView showSignupPage() {
//       return new ModelAndView("product");
//    }
	
	 @RequestMapping(value = "/productList", method = RequestMethod.GET)
	    public ModelAndView getAllProducts(@ModelAttribute("User") User lub) {
	        List<Product> products = productDao.allproduct();
	        ModelAndView modelAndView = new ModelAndView("products");
	        modelAndView.addObject("products", products);
	        return modelAndView;
	    }

}
