package com.fastquick.controller;

import com.fastquick.data.dto.OrderDTO;
import com.fastquick.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    public String orderList(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		Long userId = (Long)session.getAttribute("id");
		model.addAttribute("orders", orderService.getReadyOrders(userId));
    	return "order/orderList";
    }

	@GetMapping("/update")
	public String getAPIorder()
	{

		return "order/orderList";
	}
    
    @GetMapping("/orderDetail")
    public String orderDetail() {
    	return "order/orderDetail";
    }


}
