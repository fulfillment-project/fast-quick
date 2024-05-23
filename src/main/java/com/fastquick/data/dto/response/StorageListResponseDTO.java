package com.fastquick.data.dto.response;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class StorageListResponseDTO {
	
	private String division;
	private Integer storageId;
    private String productName;
    private String quantity;
    private String bigo;
    private LocalDateTime insertDateTime;
    private LocalDateTime updateDateTime;
    
	public StorageListResponseDTO(String division, Integer storageId, String productName, String quantity, String bigo, LocalDateTime insertDateTime, LocalDateTime updateDateTime) {
		this.division = division;
		this.storageId = storageId;
		this.productName = productName;
		this.quantity = quantity;
		this.bigo = bigo;
		this.insertDateTime = insertDateTime;
		this.updateDateTime = updateDateTime;
	}
    
    
}
