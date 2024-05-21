package com.fastquick.data.dto.request;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class StorageCreateRequestDTO {
	
	@NonNull
	private String productName;
	
	@NonNull
	private Integer count;
	
	//주소
	@NonNull
	private String zipcode;
	
	//비고
	private String bigo;
	
	
}
