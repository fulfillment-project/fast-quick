package com.fastquick.controller;

import com.fastquick.data.dto.response.ProductListResponseDTO;
import com.fastquick.data.dto.response.StorageListResponseDTO;
import com.fastquick.data.entity.Product;
import com.fastquick.data.entity.StorageRetrieval;
import com.fastquick.service.impl.ProductServiceImpl;
import com.fastquick.service.impl.StorageServiceImpl;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MainController {

    private final StorageServiceImpl storageService;
    private final ProductServiceImpl productService;

    public MainController(StorageServiceImpl storageService, ProductServiceImpl productService) {
        this.storageService = storageService;
        this.productService = productService;
    }

    @GetMapping("/index")
    public ModelAndView index() {
        List<StorageListResponseDTO> importList = this.storageService.importStorageList();
        List<StorageListResponseDTO> exportList = this.storageService.exportStorageList();
        List<ProductListResponseDTO> productList = this.productService.findSafeProducts();
        List<Product> productCount = this.productService.findAllProducts();
        List<StorageRetrieval> divisionImport = this.storageService.findAllStorageRetrievals();
        
        //입고 수 카운트
        long divisionImportCount = divisionImport.stream()
        								.filter(Item -> "1".equals(Item.getDivision()))
        								.count();
        // 출고 수 카운트
        long divisionExportCount = divisionImport.stream()
        								.filter(Item -> "2".equals(Item.getDivision()))
        								.count();
        
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        mav.addObject("importList", importList);
        mav.addObject("exportList", exportList);
        mav.addObject("productList", productList);
        mav.addObject("productCount", productCount);
        mav.addObject("divisionImportCount", divisionImportCount);
        mav.addObject("divisionExportCount", divisionExportCount);
        return mav;
    }
    
}
