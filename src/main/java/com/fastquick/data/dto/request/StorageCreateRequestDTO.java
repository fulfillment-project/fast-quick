package com.fastquick.data.dto.request;

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
	
	private String address;
	
	private String bigo;
	
	
}
