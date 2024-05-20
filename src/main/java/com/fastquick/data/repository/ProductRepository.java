package com.fastquick.data.repository;

import com.fastquick.data.entity.Product;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
	public List<Product> findByProductName(String productName, Pageable pageable) ;
}
