package com.fastquick.controller;

import com.fastquick.data.dto.OrderDTO;
import com.fastquick.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/order")
public class OrderController {
	private final OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@GetMapping("/orderList")
    public String orderList(Model model) {
//		model.addAttribute("orders", orderService.postWithParamAndBody());
    	return "order/orderList";
    }
    
    @GetMapping("/orderDetail")
    public String orderDetail() {
    	return "order/orderDetail";
    }


	@GetMapping
	public String getName() {
		return orderService.getName();
	}
}
