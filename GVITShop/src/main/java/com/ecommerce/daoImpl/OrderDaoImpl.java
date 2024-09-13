package com.ecommerce.daoImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.dao.OrderDao;
import com.ecommerce.dao.ProductDao;
import com.ecommerce.model.AddressForm;
import com.ecommerce.model.Order;
import com.ecommerce.model.OrderItem;
import com.ecommerce.model.Product;
import com.ecommerce.model.User;
import com.ecommerce.repository.OrderRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderDaoImpl implements OrderDao{
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Transactional
	@Override
	public boolean createOrder(User user, Long productId, int quantity, AddressForm addressForm) {
	    Product product = productDao.findById(productId);

	    if (product == null || quantity <= 0) {
	        return false;
	    }

	    double totalPrice = product.getPrice() * quantity;

	    Order order = new Order();
	    order.setUser(user);
	    order.setTotalPrice(totalPrice);
	    order.setOrderDate(LocalDateTime.now());

	    // Set AddressForm directly
	    order.setDeliveryAddress(addressForm);

	    OrderItem orderItem = new OrderItem();
	    orderItem.setProduct(product);
	    orderItem.setQuantity(quantity);
	    orderItem.setPrice(product.getPrice());

	    List<OrderItem> orderItems = new ArrayList<>();
	    orderItems.add(orderItem);
	    order.setOrderItems(orderItems);

	    orderRepository.save(order);

	    return true;
	}


}
