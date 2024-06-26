package com.fastquick.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.fastquick.data.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.fastquick.data.dto.request.StorageCreateRequestDTO;
import com.fastquick.data.dto.response.ProductListResponseDTO;
import com.fastquick.data.dto.response.StorageListResponseDTO;
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
    public ModelAndView storageList(String productName, Integer page, ModelAndView mav) {
    	
    	if (page == null || 0 > page) {
    		page = 1;
    	}
    	
    	final int pageSize = 5;
    	long totalCount = storageService.countTotalStorages(productName);
    	int totalPages = (int) Math.ceil((double) totalCount / pageSize);

    	mav.setViewName("/storage/storageList");
    	
    	List<StorageListResponseDTO> storages = this.storageService.storageList(productName, page);
    	
    	mav.addObject("page", page);
    	mav.addObject("totalPages", totalPages);
    	mav.addObject("storages", storages);
    	
    	return mav;
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
