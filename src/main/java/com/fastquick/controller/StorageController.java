package com.fastquick.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.fastquick.data.dto.request.StorageCreateRequestDTO;
import com.fastquick.data.dto.response.ProductListResponseDTO;
import com.fastquick.service.ProductService;
import com.fastquick.service.StorageService;

@Controller
@RequestMapping("/storage")
public class StorageController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private StorageService storageService;

    @GetMapping("/storageList")
    public String storageList() {
    	return "storage/storageList";
    }
    
    @GetMapping("/storageRequest")
    public ModelAndView storageRequest(String productName, Integer page, ModelAndView mav) {
    	List<ProductListResponseDTO> products = this.productService.productList(productName, page);
    	mav.addObject("products", products);
    	
    	mav.setViewName("storage/storageRequest");
    	return mav;
    }
    
    @PostMapping("/storageRequest")
    public String storageCreate(StorageCreateRequestDTO storageCreateRequestDTO) {
    	Integer storageId = this.storageService.storageCreate(storageCreateRequestDTO);
    	return String.format("redirect:/storage/storageList");
    }
}
