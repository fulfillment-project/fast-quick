package com.fastquick.data.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.fastquick.data.entity.Product;
import com.fastquick.data.entity.StorageRetrieval;

public interface StorageRepository extends JpaRepository<StorageRetrieval, Integer>{
	public List<StorageRetrieval> findByProductNameContaining(@Param("productName") String productName, Pageable pageable) ;

	public long countByProductNameContaining(@Param("productName") String productName);

	public List<StorageRetrieval> findTop5ByDivisionOrderByInsertDateTimeDesc(String division);
}
