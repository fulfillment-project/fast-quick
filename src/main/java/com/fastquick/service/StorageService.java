package com.fastquick.service;

import java.util.List;

import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoProperties.Storage;

import com.fastquick.data.dto.request.StorageCreateRequestDTO;
import com.fastquick.data.dto.response.StorageListResponseDTO;
import com.fastquick.data.entity.StorageRetrieval;

public interface StorageService {
	
	public Integer storageCreate(StorageCreateRequestDTO storageCreateRequestDTO); 
	
	public List<StorageListResponseDTO> storageList(String ProductName, Integer page);

	public long countTotalStorages(String productName);
	
	public List<StorageRetrieval> findAllStorageRetrievals();

	public List<StorageListResponseDTO> importStorageList();

	public List<StorageListResponseDTO> exportStorageList();
}
