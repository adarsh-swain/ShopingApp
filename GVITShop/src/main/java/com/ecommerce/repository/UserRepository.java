package com.ecommerce.repository;

import com.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmailAndPassword(String email, String password);
	Optional<User> findByEmail(String email);
}
