package com.fastquick.data.dto.response;

import lombok.*;

@Getter
public class StockListResponseDTO {
	private Integer productId;
    private String productName;
    private Integer importAmount;
    private Integer exportAmount;
    private Integer quantity;
    private Integer safeQuantity;
    
    public StockListResponseDTO(Integer productId, String productName, Integer importAmount, Integer exportAmount, Integer quantity, Integer safeQuantity) {
    	this.productId = productId;
    	this.productName = productName;
    	this.importAmount = importAmount;
    	this.exportAmount = exportAmount;
    	this.quantity = quantity;
    	this.safeQuantity = safeQuantity;
    }
}
