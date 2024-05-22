package com.fastquick.data.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.fastquick.data.entity.Product;

import java.util.List;

import org.springframework.data.domain.Pageable;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	public List<Product> findByProductNameContaining(@Param("productName") String productName, Pageable pageable) ;
	public long countByProductNameContaining(@Param("productName") String productName);
}
