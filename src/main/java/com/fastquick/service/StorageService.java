package com.fastquick.service;

import java.util.List;

import com.fastquick.data.dto.request.StorageCreateRequestDTO;
import com.fastquick.data.dto.response.StorageListResponseDTO;

public interface StorageService {
	
	public Integer storageCreate(StorageCreateRequestDTO storageCreateRequestDTO); 
	
	public List<StorageListResponseDTO> StorageList(String ProductName, Integer page);
	
	
}
