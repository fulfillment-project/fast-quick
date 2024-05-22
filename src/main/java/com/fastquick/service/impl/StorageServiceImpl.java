package com.fastquick.service.impl;

import org.springframework.stereotype.Service;

import com.fastquick.data.dto.request.StorageCreateRequestDTO;
import com.fastquick.data.entity.StorageRetrieval;
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
		StorageRetrieval storageRetrieval = StorageRetrieval.builder()
				.productName(storageCreateRequestDTO.getProductName())
				.member(storageCreateRequestDTO.getMemberId())
				.count(storageCreateRequestDTO.getCount())
				.warehouse(storageCreateRequestDTO.getWarehouse())
				.zipcode(storageCreateRequestDTO.getZipcode())
				.address(storageCreateRequestDTO.getAddress())
				.addressDetail(storageCreateRequestDTO.getAddressDetail())
				.bigo(storageCreateRequestDTO.getBigo())
				.shopProduct(storageCreateRequestDTO.getShopProductId())
				.build();
			this.storageRepository.save(storageRetrieval);
		return storageRetrieval.getStorageId();
	}

}
