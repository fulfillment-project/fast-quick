package com.fastquick.data.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.fastquick.data.entity.Product;

import java.util.List;

import org.springframework.data.domain.Pageable;

public interface ProductRepository extends JpaRepository<Product, Long> {
	public List<Product> findByProductNameContaining(String productName, Pageable pageable) ;
}
