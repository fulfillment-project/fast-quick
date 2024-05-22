package com.fastquick.service.impl;

import org.springframework.stereotype.Service;

import com.fastquick.data.dto.request.StorageCreateRequestDTO;
import com.fastquick.data.entity.Member;
import com.fastquick.data.entity.StorageRetrieval;
import com.fastquick.data.repository.MemberRepository;
import com.fastquick.data.repository.StorageRepository;
import com.fastquick.service.StorageService;

@Service
public class StorageServiceImpl implements StorageService{

	private final StorageRepository storageRepository;
	private final MemberRepository memberRepository;
	
	public StorageServiceImpl(StorageRepository storageRepository, MemberRepository memberRepository) {
		this.storageRepository = storageRepository;
		 this.memberRepository = memberRepository;
	}

	@Override
	public Integer storageCreate(StorageCreateRequestDTO storageCreateRequestDTO) {
		Member member = memberRepository.findById(storageCreateRequestDTO.getMemberId())
	            .orElseThrow(() -> new IllegalArgumentException("Invalid member ID"));

	    StorageRetrieval storageRetrieval = new StorageRetrieval();
	    storageRetrieval.setProductName(storageCreateRequestDTO.getProductName());
	    storageRetrieval.setMember(member);
	    storageRetrieval.setCount(storageCreateRequestDTO.getCount());
	    storageRetrieval.setWarehouse(storageCreateRequestDTO.getWarehouse());
	    storageRetrieval.setZipcode(storageCreateRequestDTO.getZipcode());
	    storageRetrieval.setAddress(storageCreateRequestDTO.getAddress());
	    storageRetrieval.setAddressDetail(storageCreateRequestDTO.getAddressDetail());
	    storageRetrieval.setBigo(storageCreateRequestDTO.getBigo());
	    storageRetrieval.setShopProductId(storageCreateRequestDTO.getShopProductId());

	    this.storageRepository.save(storageRetrieval);
	    return storageRetrieval.getStorageId();
	}

}
