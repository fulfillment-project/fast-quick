package com.fastquick.data.dto.response;

import lombok.Getter;

@Getter
public class StorageListResponseDTO {
	
	private String division;
	private Integer storageId;
    private String productName;
    private String quantity;
    private String bigo;

    
	public StorageListResponseDTO(String division, Integer storageId, String productName, String quantity, String bigo) {
		this.division = division;
		this.storageId = storageId;
		this.productName = productName;
		this.quantity = quantity;
		this.bigo = bigo;

	}
    
    
}
