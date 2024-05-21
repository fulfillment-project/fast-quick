package com.fastquick.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fastquick.data.entity.StorageRetrieval;

public interface StorageRepository extends JpaRepository<StorageRetrieval, Integer>{

}
