package com.fastquick.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.fastquick.data.dto.response.ShopProductListResponseDTO;
import com.fastquick.service.impl.ShopProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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

    @Autowired
    private ShopProductServiceImpl shopProductService;
    	
    @GetMapping("/productList")
    public ModelAndView productList(String productName, Integer page, ModelAndView mav) {
    	
    	if (productName == null) {
    	        productName = "";
    	}
    	
    	if (page == null || 0 > page) {
    		page = 1;
    	}
    	
        final int pageSize = 10;
        long totalCount = productService.countTotalProducts(productName);
        
    	int totalPages = (int) Math.ceil((double) totalCount / pageSize);
        
        List<ProductListResponseDTO> products = this.productService.productList(productName, page);

        if (mav == null) {
            mav = new ModelAndView("/product/productList");
        } else {
            mav.clear();
            mav.setViewName("/product/productList");
        }
        
        mav.addObject("page", page);
        mav.addObject("totalPages", totalPages);
        mav.addObject("products", products);
    	
    	return mav;

    }
    
    @GetMapping("/productWrite")
    public String writePage() {
    	return "product/productWrite";
    }
    
    /*@PostMapping("/productDelete")
    public String productDelete(@RequestParam("productId") Integer productId) {
    	this.productService.productDelete(productId);
    	return "redirect:/product/productList";
    }*/
    
    @PostMapping("/productWrite")
    public String productWrite(HttpServletRequest request, ProductWriteRequestDTO productWriteRequestDTO) {
    	HttpSession session = request.getSession();
        Integer memberId = (Integer) session.getAttribute("memberId");
    	
        if (memberId == null) {
            return "redirect:/member/login";  // 로그인 페이지로 리다이렉트
        }
        
        productWriteRequestDTO.setMemberId(memberId);
        
    	Integer productId = this.productService.productInsert(productWriteRequestDTO);
    	return String.format("redirect:/product/productList");
    }
    
    @GetMapping(value = ("/productDetail/{productId}"))
    public ResponseEntity<Map<String, Object>> detail(@PathVariable("productId") Integer productId) throws NoSuchElementException {
        ProductDetailResponseDTO productDetailResponseDTO = this.productService.detailProduct(productId);
        List<ShopProductListResponseDTO> productListResponseDTOList = this.shopProductService.selectShopProduct(productId);
        Map<String,Object> map = new HashMap<>();
        Map<String,Object> data = new HashMap<>();
        data.put("product", productDetailResponseDTO);
        data.put("shopList", productListResponseDTOList);
        map.put("data", data);
        return ResponseEntity.ok().body(map);
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
