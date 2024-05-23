package com.fastquick.controller;

import com.fastquick.exception.StockStarvationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController {

	@ExceptionHandler(Exception.class)
	public String helloEror(Exception e, Model model) {
		model.addAttribute("error", e.getMessage());
		return "error";
	}

	@ExceptionHandler(StockStarvationException.class)
	public String handleStockStarvation(StockStarvationException e, Model model) {
		model.addAttribute("error", e.getMessage());
		return "error";
	}
}
