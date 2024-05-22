package com.fastquick.service;

import com.fastquick.data.dto.request.StorageCreateRequestDTO;

public interface StorageService {
	
	public Integer storageCreate(StorageCreateRequestDTO storageCreateRequestDTO); 
	
	public List<StorageListResponseDTO> StorageList(String ProductName, Integer page);
	
	
}
