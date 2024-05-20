package com.fastquick.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.fastquick.data.dto.response.ProductDetailResponseDTO;
import com.fastquick.data.entity.Product;
import com.fastquick.data.repository.ProductRepository;
import com.fastquick.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService  {
	private ProductRepository productRepository;
	
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public List<ProductDetailResponseDTO> listProduct() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductDetailResponseDTO detailProduct(Long productId) throws NoSuchElementException{
		Product product = this.productRepository.findById(productId).orElseThrow();
		
		return ProductDetailResponseDTO.ProductFactory(product);
	}

	@Override
	public ProductDetailResponseDTO getProduct(Integer productId) {
		// TODO Auto-generated method stub
		return null;
	}	
}
