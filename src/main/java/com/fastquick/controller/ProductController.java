package com.fastquick.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product")
public class ProductController {

    @GetMapping("/productList")
    public String productList() {
    	return "product/productList";
    }
    
    @GetMapping("/productWrite")
    public String productWrite() {
    	return "product/productWrite";
    }
}
