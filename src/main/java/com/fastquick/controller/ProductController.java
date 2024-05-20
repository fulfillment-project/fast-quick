package com.fastquick.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.fastquick.data.dto.response.ProductListResponseDTO;
import com.fastquick.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
    @GetMapping("/productList")
    public ModelAndView productList(String productName, Integer page, ModelAndView mav) {
    	mav.setViewName("/product/productList");
    	
    	List<ProductListResponseDTO> products = this.productService.productList(productName, page);
    	mav.addObject("products", products);

    	System.out.println(products);
    	
    	return mav;
    }
    
    @GetMapping("/productWrite")
    public String productWrite() {
    	return "product/productWrite";
    }
}
