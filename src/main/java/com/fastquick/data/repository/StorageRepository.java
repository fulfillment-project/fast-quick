package com.fastquick.data.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fastquick.data.entity.Product;
import com.fastquick.data.entity.StorageRetrieval;

public interface StorageRepository extends JpaRepository<StorageRetrieval, Integer>{
	public List<Product> findByProductNameContaining(String productName, Pageable pageable) ;

	public long countByProductNameContaining(String productName);
}
