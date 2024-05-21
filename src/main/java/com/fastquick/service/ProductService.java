package com.fastquick.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.fastquick.data.dto.request.ProductWriteRequestDTO;
import com.fastquick.data.dto.response.ProductDetailResponseDTO;
import com.fastquick.data.dto.response.ProductListResponseDTO;


public interface ProductService {
	
  public List<ProductListResponseDTO> productList(String productName, Integer page);
  public ProductDetailResponseDTO detailProduct(Integer productId);
  public Integer productInsert(ProductWriteRequestDTO productWriteRequestDTO);
}
