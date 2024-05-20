package com.fastquick.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.fastquick.data.dto.response.ProductDetailResponseDTO;
import com.fastquick.service.ProductService;

import java.util.NoSuchElementException;

	@Controller
	@RequestMapping("/product")
	public class ProductController {
    
    @Autowired
    private ProductService productService;
    
    @GetMapping("/productList")
    public String productList() {
        return "product/productList";
    }
    
    // 등록
    @GetMapping("/productWrite")
    public String productWrite() {
        return "product/productWrite";
    }
    
    @GetMapping(value = ("/productDetail/{productId}"))
    public ModelAndView detail(@PathVariable("productId") Long productId) throws NoSuchElementException {
        ModelAndView mav = new ModelAndView();
        ProductDetailResponseDTO productDetailResponseDTO = this.productService.detailProduct(productId);
        mav.addObject("productDetailResponseDTO", productDetailResponseDTO);
        return mav;
    }
}
