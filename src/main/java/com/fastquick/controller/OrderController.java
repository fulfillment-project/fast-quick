package com.fastquick.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order")
public class OrderController {

    @GetMapping("/orderList")
    public String orderList() {
    	return "order/orderList";
    }
    
    @GetMapping("/orderDetail")
    public String orderDetail() {
    	return "order/orderDetail";
    }
}
