package com.fastquick.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fastquick.data.dto.response.ProductDetailResponseDTO;

@Service
public interface ProductService {
	
	//목록조회
	List<ProductDetailResponseDTO> listProduct();
	
	// 상세
	ProductDetailResponseDTO detailProduct(Long productId);
	
	// 등록
	ProductDetailResponseDTO getProduct(Integer productId);
	
	

}
