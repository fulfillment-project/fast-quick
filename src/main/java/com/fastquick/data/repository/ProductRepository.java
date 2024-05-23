package com.fastquick.data.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fastquick.data.entity.Product;

import java.util.List;

import org.springframework.data.domain.Pageable;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	public List<Product> findByProductNameContaining(@Param("productName") String productName, Pageable pageable) ;

	public long countByProductNameContaining(@Param("productName") String productName);

	//지정한 안전재고량에 근접한 상품 목록
	@Query(value = "SELECT (p.quantity - p.safe_quantity) AS difference, p.* FROM product p ORDER BY difference ASC LIMIT 1, 5", nativeQuery = true)
	public List<Product> findByDangerous();

}
