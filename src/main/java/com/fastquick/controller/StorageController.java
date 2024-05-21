package com.fastquick.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.fastquick.data.dto.request.StorageCreateRequestDTO;
import com.fastquick.service.StorageService;

@Controller
@RequestMapping("/storage")
public class StorageController {
	
	@Autowired
	private StorageService storageService;

    @GetMapping("/storageList")
    public String storageList() {
    	return "storage/storageList";
    }
    
    @GetMapping("/storageRequest")
    public String storageRequest() {
    	return "storage/storageRequest";
    }
    
    @PostMapping("/storageRequest")
    public String storageCreate(StorageCreateRequestDTO storageCreateRequestDTO) {
    	Integer storageId = this.storageService.storageCreate(storageCreateRequestDTO);
    	return String.format("redirect:/storage/storageList");
    }
}
