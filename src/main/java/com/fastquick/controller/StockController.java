package com.fastquick.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.fastquick.data.dto.response.StockDetailResponseDTO;
import com.fastquick.data.dto.response.StockListResponseDTO;
import com.fastquick.service.ProductService;

@Controller
@RequestMapping("/stock")
public class StockController {

	@Autowired
	private ProductService productService;
	
    @GetMapping("/stockList")
    public ModelAndView stockList(String productName, Integer page, ModelAndView mav) {
    	
    	if (productName == null) {
    		productName = "";
    	}
	
    	if (page == null || 0 > page) {
    		page = 1;
    	}
    	
    	 final int pageSize = 10;
         long totalCount = productService.countTotalProducts(productName);
         
     	int totalPages = (int) Math.ceil((double) totalCount / pageSize);
    	
     	List<StockListResponseDTO> stock = this.productService.stockList(productName, page);
     	
     	if (mav == null) {
            mav = new ModelAndView("/stock/stockList");
        } else {
            mav.clear();
            mav.setViewName("/stock/stockList");
        }
        
        mav.addObject("page", page);
        mav.addObject("totalPages", totalPages);
        mav.addObject("stock", stock);
    	
    	return mav;
     	
    }
    
    @GetMapping(value = ("/stockDetail/{productId}"))
    public ResponseEntity<StockDetailResponseDTO>  stockDetail(@PathVariable("productId") Integer productId) throws NoSuchElementException {
    	StockDetailResponseDTO stockDetailResponseDTO = this.productService.detailStock(productId);
    	return ResponseEntity.ok().body(stockDetailResponseDTO);
    }
}
