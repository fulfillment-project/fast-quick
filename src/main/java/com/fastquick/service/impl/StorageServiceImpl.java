package com.fastquick.service.impl;

import org.springframework.stereotype.Service;

import com.fastquick.data.dto.request.StorageCreateRequestDTO;
import com.fastquick.data.entity.Storage;
import com.fastquick.data.repository.StorageRepository;
import com.fastquick.service.StorageService;

@Service
public class StorageServiceImpl implements StorageService{

	private final StorageRepository storageRepository;
	
	public StorageServiceImpl(StorageRepository storageRepository) {
		this.storageRepository = storageRepository;
	}

	@Override
	public Integer storageCreate(StorageCreateRequestDTO storageCreateRequestDTO) {
		Storage storage = Storage.builder()
				.productName(storageCreateRequestDTO.getProductName())
				.count(storageCreateRequestDTO.getCount())
				.warehouse(storageCreateRequestDTO.getWarehouse())
				.address(storageCreateRequestDTO.getAddress())
				.bigo(storageCreateRequestDTO.getBigo())
				.build();
			this.storageRepository.save(storage);
		return storage.getStorageId();
	}

}
