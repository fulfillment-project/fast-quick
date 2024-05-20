package com.fastquick.data.dto.response;

import java.time.LocalDateTime;

import com.fastquick.data.entity.Product;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ProductDetailResponseDTO {
	private Integer productId;
	private String productName;
	private Integer quantity;
	private Long barcode;
	private LocalDateTime insertDateTime;
	private LocalDateTime updateDateTime;
	
	public ProductDetailResponseDTO fromProduct(Product product) {

		this.productId = product.getProductId();
		this.productName = product.getProductName();
		this.quantity = product.getQuantity();
		this.barcode = product.getBarcode();
		this.insertDateTime = product.getInsertDateTime();
		this.updateDateTime = product.getUpdateDateTime();
		
		return this;
	}
	
	public static ProductDetailResponseDTO ProductFactory(Product product) {
		ProductDetailResponseDTO productDetailResponseDTO = new ProductDetailResponseDTO();
		productDetailResponseDTO.fromProduct(product);
		
		return productDetailResponseDTO;
	}
	
}
