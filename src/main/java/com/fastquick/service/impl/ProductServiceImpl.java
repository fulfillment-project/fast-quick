package com.fastquick.service.impl;

import com.fastquick.data.dto.request.ProductWriteRequestDTO;
import com.fastquick.data.dto.response.ProductDetailResponseDTO;
import com.fastquick.data.dto.response.ProductListResponseDTO;
import com.fastquick.data.entity.Member;
import com.fastquick.data.entity.Product;
import com.fastquick.data.repository.MemberRepository;
import com.fastquick.data.repository.ProductRepository;
import com.fastquick.service.MemberService;
import com.fastquick.service.ProductService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;

    public ProductServiceImpl(ProductRepository productRepository, MemberRepository memberRepository) {
        this.productRepository = productRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public Integer productInsert(ProductWriteRequestDTO productWriteRequestDTO) {
    	 Member member = memberRepository.findById(productWriteRequestDTO.getMemberId())
                 .orElseThrow(() -> new IllegalArgumentException("Invalid member ID"));
    	
    	 Product product = new Product();
    	    product.setProductName(productWriteRequestDTO.getProductName());
    	    product.setQuantity(productWriteRequestDTO.getQuantity());
    	    product.setSafeQuantity(productWriteRequestDTO.getSafeQuantity());
    	    product.setBarcode(productWriteRequestDTO.getBarcode());
    	    product.setImage(productWriteRequestDTO.getImage());
    	    product.setMemberId(member);
    	
    			this.productRepository.save(product);
    			return product.getProductId();
    }
    
    
  	@Override
    public ProductDetailResponseDTO detailProduct(Integer productId) throws NoSuchElementException{
      Product product = this.productRepository.findById(productId).orElseThrow();
      ProductDetailResponseDTO productDetailResponseDTO = new ProductDetailResponseDTO();
      productDetailResponseDTO.fromProduct(product);
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
			products = this.productRepository.findByProductNameContaining(productName, pageable);
    	}   
    	
    	return products.stream().map(product ->
    			new ProductListResponseDTO(product.getProductId(), product.getProductName(), product.getBarcode(), product.getQuantity())
    			).collect(Collectors.toList());
    	
    }
    
    public long countTotalProducts(@Param("productName") String productName) {
        if (productName == null || productName.isEmpty()) {
        	System.out.println("c11 : " + productName);
            // productName이 null이면 모든 상품의 수를 반환
            return productRepository.count();
        } else {
        	System.out.println("c22: " + productName);
            // productName이 포함되는 상품의 수를 반환
            return productRepository.countByProductNameContaining(productName);
        }
    }

	@Override
	public List<Product> findAllProducts() {
		return productRepository.findAll();
	}


}
