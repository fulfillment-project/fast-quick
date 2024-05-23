package com.fastquick.data.dto.response;

import java.time.LocalDateTime;

import com.fastquick.data.entity.Product;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class StockDetailResponseDTO {
	private Integer productId;
	private String productName;
	private Integer quantity;
	private Integer tempQuantity;
	private Long barcode;
	private Integer safeQuantity;
	
	public StockDetailResponseDTO fromProduct(Product product) {

		this.productId = product.getProductId();
		this.productName = product.getProductName();
		this.quantity = product.getQuantity();
		this.barcode = product.getBarcode();
		this.safeQuantity = product.getSafeQuantity();
		this.tempQuantity = product.getTempQuantity();
		
		return this;
	}
	
	public static StockDetailResponseDTO ProductFactory(Product product) {
		StockDetailResponseDTO productDetailResponseDTO = new StockDetailResponseDTO();
		productDetailResponseDTO.fromProduct(product);
		
		return productDetailResponseDTO;
	}
	
}
