package com.fastquick.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    public String storageCreate(HttpServletRequest request, StorageCreateRequestDTO storageCreateRequestDTO) {
    	HttpSession session = request.getSession();
        Integer memberId = (Integer) session.getAttribute("memberId");
    	
        if (memberId == null) {
            return "redirect:/member/login";  // 로그인 페이지로 리다이렉트
        }
        storageCreateRequestDTO.setMemberId(memberId);
    	
    	Integer storageId = this.storageService.storageCreate(storageCreateRequestDTO);
    	return String.format("redirect:/storage/storageList");
    }
    
}
