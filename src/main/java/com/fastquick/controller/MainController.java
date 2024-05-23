package com.fastquick.controller;

import com.fastquick.data.dto.response.CountResponseDTO;
import com.fastquick.data.dto.response.ProductListResponseDTO;
import com.fastquick.data.dto.response.StorageListResponseDTO;
import com.fastquick.service.impl.ProductServiceImpl;
import com.fastquick.service.impl.StorageServiceImpl;
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
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        mav.addObject("importList", importList);
        mav.addObject("exportList", exportList);
        mav.addObject("productList", productList);
        return mav;
    }
    
}
