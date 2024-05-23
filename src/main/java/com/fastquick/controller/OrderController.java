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
		Integer userId = (Integer) session.getAttribute("memberId");
		model.addAttribute("orders", orderService.getReadyOrders(userId));
		return "order/orderList";
	}

	@GetMapping("/update")
	public String getAPIorder(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("memberId");
		orderService.updateOrderById(userId);
		return "redirect:/order/orderList";
	}

	@GetMapping("/orderDetail/{productId}")
	public String orderDetail(@PathVariable Integer productId, Model model) {
		model.addAttribute("orderDetails", orderService.getReadyOrders(productId));
		return "order/orderDetail";
	}

	@GetMapping("/release/{productId}")
	public String orderRelease(@PathVariable Integer productId) {
		orderService.releaseOrder(productId);
		return "redirect:/order/orderList";
	}

	@GetMapping("/cancel/{productId}")
	public String orderCancel(@PathVariable Integer productId) {
		orderService.cancelOrder(productId);
		return "redirect:/order/orderList";
	}
}
