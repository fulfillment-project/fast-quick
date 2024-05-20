package com.fastquick.service.impl;

import com.fastquick.data.dto.response.ProductListResponseDTO;
import com.fastquick.data.entity.Member;
import com.fastquick.data.entity.Product;
import com.fastquick.data.repository.MemberRepository;
import com.fastquick.data.repository.ProductRepository;
import com.fastquick.service.MemberService;
import com.fastquick.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

  	@Override
    public ProductDetailResponseDTO detailProduct(Long productId) throws NoSuchElementException{
      Product product = this.productRepository.findById(productId).orElseThrow();

      return ProductDetailResponseDTO.ProductFactory(product);
    }
  
    @Override
    public List<ProductListResponseDTO> productList(String productName, Integer page) {
    	final int pageSize = 10;
    	
    	List<Product> products;
    	
    	if(page == null) {
			page = 0;
		} else {
			page -=1;
		}
    	
    	if(productName == null) {
    		Pageable pageable = PageRequest.of(page, pageSize, Direction.DESC , "insertDateTime");
    		products = this.productRepository.findAll(pageable).toList();
    	} else {
    		Pageable pageable = PageRequest.of(page, pageSize, Direction.DESC , "insertDateTime");
    		
    		Sort sort = Sort.by(Order.desc("insertDateTime"));
			pageable.getSort().and(sort);
			products = this.productRepository.findByProductName(productName, pageable);
    	}   
    	
    	return products.stream().map(product ->
    			new ProductListResponseDTO(product.getProductName(), product.getBarcode(), product.getQuantity())
    			).collect(Collectors.toList());
    	
    }
}
