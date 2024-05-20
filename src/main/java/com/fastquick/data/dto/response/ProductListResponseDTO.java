package com.fastquick.data.dto.response;

import lombok.*;

@Getter
public class ProductListResponseDTO {
    private String productName;
    private Long barcode;
    private Integer quantity;
    
    public ProductListResponseDTO(String productName, Long barcode, Integer quantity) {
    	this.productName = productName;
    	this.barcode = barcode;
    	this.quantity = quantity;
    }
}
