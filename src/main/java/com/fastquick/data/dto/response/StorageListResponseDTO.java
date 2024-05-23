package com.fastquick.data.dto.response;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class StorageListResponseDTO {
	
	private String division;
	private Integer storageId;
    private String productName;
    private Integer count;
    private String bigo;
	private Integer quantity;
    private LocalDateTime insertDateTime;
    private LocalDateTime updateDateTime;
    
	public StorageListResponseDTO(String division, Integer storageId, String productName, Integer count, String bigo, LocalDateTime insertDateTime, LocalDateTime updateDateTime, Integer quantity) {
		this.division = division;
		this.storageId = storageId;
		this.productName = productName;
		this.count = count;
		this.bigo = bigo;
		this.quantity = quantity;
		this.insertDateTime = insertDateTime;
		this.updateDateTime = updateDateTime;
	}
    
    
}
