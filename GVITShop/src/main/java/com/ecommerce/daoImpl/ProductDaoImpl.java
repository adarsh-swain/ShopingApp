package com.ecommerce.daoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.dao.ProductDao;
import com.ecommerce.model.Product;
import com.ecommerce.repository.ProductRepository;

@Service
public class ProductDaoImpl implements ProductDao{
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public String saveProduct(Product product) {
		productRepository.save(product);
		return "Product Added";
	}

	@Override
	public List<Product> allproduct() {
	    return productRepository.findAll();
	}
	
	public Product findById(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }


}
