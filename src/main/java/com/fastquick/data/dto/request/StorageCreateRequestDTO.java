package com.fastquick.data.dto.request;

import com.fastquick.data.entity.Member;
import com.fastquick.data.entity.ShopProduct;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StorageCreateRequestDTO {
	
	private Integer storageId;
	
	private String productName;
	
	private Integer count;
	
	private String warehouse;
	
	private String zipcode;
	
	private String address;
	
	private String addressDetail;
	
	private String bigo;
	
	private Integer memberId;
	
	private Integer shopProductId;
	
	
}
