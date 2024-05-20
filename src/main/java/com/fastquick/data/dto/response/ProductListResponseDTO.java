package com.fastquick.data.dto.response;

import lombok.*;

@Getter
public class ProductListResponseDTO {
	private Long productId;
    private String productName;
    private Long barcode;
    private Integer quantity;
    
    public ProductListResponseDTO(Long productId, String productName, Long barcode, Integer quantity) {
    	this.productId = productId;
    	this.productName = productName;
    	this.barcode = barcode;
    	this.quantity = quantity;
    }
}
