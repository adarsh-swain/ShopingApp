package com.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.model.CartItem;
import com.ecommerce.model.User;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{
	List<CartItem> findByUser(User user);

}
