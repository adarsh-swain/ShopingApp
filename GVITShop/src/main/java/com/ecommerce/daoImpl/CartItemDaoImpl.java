package com.ecommerce.daoImpl;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.dao.CartItemDao;
import com.ecommerce.dao.ProductDao;
import com.ecommerce.model.CartItem;
import com.ecommerce.model.Product;
import com.ecommerce.model.User;
import com.ecommerce.repository.CartItemRepository;
import com.ecommerce.repository.ProductRepository;

@Service
public class CartItemDaoImpl implements CartItemDao {

	@Autowired
	private CartItemRepository cartItemRepository;

	@Autowired
	private ProductRepository productRepository;

	@Override
	public void addToCart(User user, Long productId, Integer quantity) {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + productId));
		CartItem cartItem = new CartItem();
		cartItem.setProduct(product);
		cartItem.setUser(user);
		cartItem.setQuantity(quantity);
		cartItemRepository.save(cartItem);
	}

	@Override
	public List<CartItem> getUserCart(User user) {
	    return cartItemRepository.findByUser(user);
	}

	@Override
	public void removeFromCart(Long cartItemId) {
	    CartItem cartItem = cartItemRepository.findById(cartItemId)
	        .orElseThrow(() -> new NoSuchElementException("Cart item with ID " + cartItemId + " not found"));
	    cartItemRepository.delete(cartItem);
	}



}
