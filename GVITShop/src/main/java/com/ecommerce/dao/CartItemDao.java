package com.ecommerce.dao;

import java.util.List;

import com.ecommerce.model.CartItem;
import com.ecommerce.model.User;

public interface CartItemDao {
	
	public void addToCart(User user, Long productId, Integer quantity);
	
	public List<CartItem> getUserCart(User user);
	
	public void removeFromCart(Long cartItemId);
	
}
