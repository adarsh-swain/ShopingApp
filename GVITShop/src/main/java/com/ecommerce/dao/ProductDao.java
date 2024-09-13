package com.ecommerce.dao;

import java.util.List;

import com.ecommerce.model.Product;

public interface ProductDao {

	public String saveProduct(Product product);

	public List<Product> allproduct();

	public Product findById(Long productId);
}
