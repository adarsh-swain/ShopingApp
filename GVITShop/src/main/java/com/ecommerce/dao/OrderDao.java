package com.ecommerce.dao;

import com.ecommerce.model.AddressForm;
import com.ecommerce.model.User;

public interface OrderDao {
	
	 public boolean createOrder(User user, Long productId, int quantity, AddressForm addressForm);

}
