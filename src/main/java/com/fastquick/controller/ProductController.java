package com.fastquick.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.fastquick.service.ProductService;
import com.fastquick.data.dto.request.ProductWriteRequestDTO;
import com.fastquick.data.dto.response.ProductDetailResponseDTO;
import com.fastquick.data.dto.response.ProductListResponseDTO;
import com.fastquick.service.ProductService;

import java.util.NoSuchElementException;

@Controller
@RequestMapping("/product")
public class ProductController {
    
    @Autowired
    private ProductService productService;
    	
    @GetMapping("/productList")
    public ModelAndView productList(String productName, Integer page, ModelAndView mav) {
    	
    	if (productName == null) {
            productName = "";  // null 대신 빈 문자열 할당
        }
    	
    	mav.setViewName("/product/productList");
    	final int pageSize = 10;
    	
    	long totalProducts = this.productService.countTotalProducts(productName);
    	int totalPages = (int) Math.ceil((double) totalProducts / pageSize);   	

        if (page == null || page < 1) {
            page = 1;
        }

        if (page > totalPages) {
            page = totalPages;
        }
        
        List<ProductListResponseDTO> products = this.productService.productList(productName, page);
        mav.addObject("currentPage", page);
        mav.addObject("totalPages", totalPages);   
        mav.addObject("products", products);
        mav.addObject("productName", productName);
    	
    	return mav;

    }
    
    @GetMapping("/productWrite")
    public String writePage() {
    	return "product/productWrite";
    }
    
    @PostMapping("/productWrite")
    public String productWrite(ProductWriteRequestDTO productWriteRequestDTO) {
        Integer productId = this.productService.productInsert(productWriteRequestDTO);
    	return String.format("redirect:/product/productList");
    }
    
    @GetMapping(value = ("/productDetail/{productId}"))
    public ResponseEntity<ProductDetailResponseDTO> detail(@PathVariable("productId") Integer productId) throws NoSuchElementException {
        ProductDetailResponseDTO productDetailResponseDTO = this.productService.detailProduct(productId);
        return ResponseEntity.ok().body(productDetailResponseDTO);
    }
}
