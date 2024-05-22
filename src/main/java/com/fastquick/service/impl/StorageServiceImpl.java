package com.fastquick.service.impl;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fastquick.data.dto.request.StorageCreateRequestDTO;
import com.fastquick.data.dto.response.StorageListResponseDTO;
import com.fastquick.data.entity.StorageRetrieval;
import com.fastquick.data.repository.StorageRepository;
import com.fastquick.service.StorageService;

@Service
public class StorageServiceImpl implements StorageService{

	private final StorageRepository storageRepository;
	private String productName;
	
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

	@Override
	public List<StorageListResponseDTO> storageList(String productName, Integer page) {
	    final int pageSize = 5;

	    List<StorageRetrieval> storages;

	    if (page == null) {
	        page = 0;
	    } else {
	        page -= 1;
	    }

	    Pageable pageable = PageRequest.of(page, pageSize, Sort.by(Sort.Order.desc("insertDateTime")));

	    if (productName == null || productName.isEmpty()) {
	        storages = this.storageRepository.findAll(pageable).toList();
	    } else {
	        storages = this.storageRepository.findByProductNameContaining(productName, pageable);
	    }

	    return storages.stream().map(storage ->
	            new StorageListResponseDTO(storage.getDivision(), storage.getStorageId(), storage.getProductName(), storage.getQuantity(), storage.getBigo())
	    ).collect(Collectors.toList());
	}

	@Override
	public long countTotalStorages(String productName) {
		if (productName == null) {
			return storageRepository.count();
		} else {
			return storageRepository.countByProductNameContaining(productName);
		}
	}
}
