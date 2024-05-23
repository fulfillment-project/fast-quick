package com.fastquick.service;

import java.util.List;

import com.fastquick.data.dto.request.ProductWriteRequestDTO;
import com.fastquick.data.dto.response.ProductDetailResponseDTO;
import com.fastquick.data.dto.response.ProductListResponseDTO;
import com.fastquick.data.dto.response.StockListResponseDTO;
import com.fastquick.data.entity.Product;


public interface ProductService {
	
  public List<ProductListResponseDTO> productList(String productName, Integer page);
  
  public List<StockListResponseDTO> stockList(String productName, Integer page);

  public ProductDetailResponseDTO detailProduct(Integer productId);
  
  public Integer productInsert(ProductWriteRequestDTO productWriteRequestDTO);
  
  public long countTotalProducts(String productName);

  public List<Product> findAllProducts();

  public List<ProductListResponseDTO> findSafeProducts();
}
