package com.fastquick.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.fastquick.data.entity.Product;
import com.fastquick.data.repository.ProductRepository;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoProperties.Storage;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.fastquick.data.dto.request.StorageCreateRequestDTO;
import com.fastquick.data.dto.response.StorageListResponseDTO;

import com.fastquick.data.entity.Member;

import com.fastquick.data.entity.StorageRetrieval;
import com.fastquick.data.repository.MemberRepository;
import com.fastquick.data.repository.StorageRepository;
import com.fastquick.service.StorageService;

@Service
public class StorageServiceImpl implements StorageService{

	private final StorageRepository storageRepository;
	private final MemberRepository memberRepository;
	private final ProductRepository productRepository;

	public StorageServiceImpl(StorageRepository storageRepository, MemberRepository memberRepository, ProductRepository productRepository) {
		this.storageRepository = storageRepository;
		 this.memberRepository = memberRepository;
        this.productRepository = productRepository;
    }

	@Override
	public Integer storageCreate(StorageCreateRequestDTO storageCreateRequestDTO) {

		Member member = memberRepository.findById(storageCreateRequestDTO.getMemberId())
	            .orElseThrow(() -> new IllegalArgumentException("Invalid member ID"));

	    StorageRetrieval storageRetrieval = new StorageRetrieval();
	    storageRetrieval.setProductName(storageCreateRequestDTO.getProductName());
	    storageRetrieval.setDivision(storageCreateRequestDTO.getDivision());
	    storageRetrieval.setMember(member);
	    storageRetrieval.setCount(storageCreateRequestDTO.getCount());
	    storageRetrieval.setWarehouse(storageCreateRequestDTO.getWarehouse());
	    storageRetrieval.setZipcode(storageCreateRequestDTO.getZipcode());
	    storageRetrieval.setAddress(storageCreateRequestDTO.getAddress());
	    storageRetrieval.setAddressDetail(storageCreateRequestDTO.getAddressDetail());
	    storageRetrieval.setBigo(storageCreateRequestDTO.getBigo());
	    storageRetrieval.setProductId(storageCreateRequestDTO.getProductId());

		Product product = this.productRepository.findById(storageCreateRequestDTO.getProductId()).orElseThrow();
		if(product.getTempQuantity() - storageCreateRequestDTO.getCount() < 0){
			product.setQuantity(product.getQuantity() + storageCreateRequestDTO.getCount());
		} else {
			product.setTempQuantity(product.getQuantity() - storageCreateRequestDTO.getCount());
		}

		this.productRepository.save(product);

	    this.storageRepository.save(storageRetrieval);
	    return storageRetrieval.getStorageId();

	}

	@Override
	public List<StorageListResponseDTO> storageList(String productName, Integer page) {
	    final int pageSize = 5;


	    if (page == null) {
	        page = 0;
	    } else {
	        page -= 1;
	    }

	    Pageable pageable = PageRequest.of(page, pageSize, Sort.by(Sort.Order.desc("insertDateTime")));
	    
	    List<StorageRetrieval> storages;
	    
	    if (productName == null || productName.isEmpty()) {
	        storages = this.storageRepository.findAll(pageable).toList();
	    } else {
	        storages = this.storageRepository.findByProductNameContaining(productName, pageable);
	    }

	    return storages.stream()
	    		.map(storage -> new StorageListResponseDTO(
	    				storage.getDivision(),
	    				storage.getStorageId(),
	    				storage.getProductName(),
	    				storage.getCount(),
	    				storage.getBigo(),
	    				storage.getInsertDateTime(),
	    				storage.getUpdateDateTime())
	    ).collect(Collectors.toList());
	}

	@Override
	public List<StorageRetrieval> findAllStorageRetrievals() {
		return storageRepository.findAll();
	}
	
	@Override
	public long countTotalStorages(@Param("productName") String productName) {
		if (productName == null) {
			return storageRepository.count();
		} else {
			return storageRepository.countByProductNameContaining(productName);
		}
	}

	@Override
	public List<StorageListResponseDTO> importStorageList() {
		List<StorageRetrieval> storages = this.storageRepository.findTop5ByDivisionOrderByInsertDateTimeDesc("1");
		return storages.stream()
				.map(storage -> new StorageListResponseDTO(
						storage.getDivision(),
						storage.getStorageId(),
						storage.getProductName(),
						storage.getCount(),
						storage.getBigo(),
						storage.getInsertDateTime(),
						storage.getUpdateDateTime())
				).collect(Collectors.toList());
	}

	@Override
	public List<StorageListResponseDTO> exportStorageList() {
		List<StorageRetrieval> storages = this.storageRepository.findTop5ByDivisionOrderByInsertDateTimeDesc("2");
		return storages.stream()
				.map(storage -> new StorageListResponseDTO(
						storage.getDivision(),
						storage.getStorageId(),
						storage.getProductName(),
						storage.getCount(),
						storage.getBigo(),
						storage.getInsertDateTime(),
						storage.getUpdateDateTime())
				).collect(Collectors.toList());
	}
}
