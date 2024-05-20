package com.fastquick.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/stock")
public class StockController {

    @GetMapping("/stockList")
    public String stockList() {
    	return "stock/stockList";
    }
    
    @GetMapping("/stockDetail")
    public String stockDetail() {
    	return "stock/stockDetail";
    }
}
