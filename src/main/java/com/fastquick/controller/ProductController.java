package com.fastquick.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fastquick.data.dto.request.ProductWriteRequestDTO;
import com.fastquick.data.dto.response.ProductDetailResponseDTO;
import com.fastquick.data.dto.response.ProductListResponseDTO;
import com.fastquick.data.entity.Product;
import com.fastquick.data.util.ExcelGenerator;
import com.fastquick.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
    
    @Autowired
    private ProductService productService;
    	
    @GetMapping("/productList")
    public ModelAndView productList(String productName, Integer page, ModelAndView mav) {
    	
    	if (page == null || 0 > page) {
    		page = 1;
    	}
    	
        final int pageSize = 5;
        long totalCount = productService.countTotalProducts(productName);
    	int totalPages = (int) Math.ceil((double) totalCount / pageSize);
    	
    	mav.setViewName("/product/productList");
        
        List<ProductListResponseDTO> products = this.productService.productList(productName, page);

        mav.addObject("page", page);
        mav.addObject("totalPages", totalPages);
        mav.addObject("products", products);
    	
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
    
    @GetMapping("/downloadExcel")
    public ResponseEntity<InputStreamResource> downloadExcel() throws IOException {
        List<Product> products = productService.findAllProducts(); // 메서드 이름 수정
        ByteArrayInputStream in = ExcelGenerator.generateExcel(products);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=products.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(in));
    }
}
