package com.fastquick.schedule;

import com.fastquick.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Observer {

	private final OrderService orderService;
	@Scheduled(cron = "0 0 10 * *")
	public void getOrdersAt10() {
		orderService.postWithParamAndBody();
	}

	@Scheduled(cron = "0 0 14 * *")
	public void getOrdersAt14() {
		orderService.postWithParamAndBody();
	}
}
