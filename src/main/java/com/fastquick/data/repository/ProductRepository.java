package com.fastquick.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fastquick.data.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
