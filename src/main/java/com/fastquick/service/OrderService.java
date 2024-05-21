package com.fastquick.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fastquick.data.dto.OrderDTO;
import com.fastquick.data.dto.request.MemberRequestDTO;
import com.fastquick.data.entity.ProductOrder;
import com.fastquick.data.repository.ProductOrderRepository;
import com.fastquick.data.util.DeliveryStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderService {
	private final ProductOrderRepository productOrderRepository;

	// Post 형식의 RestTemplate
	public void postWithParamAndBody() {
		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:8080")
				.path("/api/get/order")
				.queryParam("name", "coh")
				.encode()
				.build()
				.toUri();
		MemberRequestDTO memberRequestDTO = MemberRequestDTO.builder()
				.id("coh")
				.pwd("1234")
				.build();

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Map> result = restTemplate.postForEntity(uri, memberRequestDTO, Map.class);
		List<LinkedHashMap> data = (List<LinkedHashMap>) result.getBody().get("data");
		ObjectMapper mapper = new ObjectMapper();
		CollectionType listType = mapper.getTypeFactory().constructCollectionType(List.class, OrderDTO.class);
		List<OrderDTO> orders = mapper.convertValue(data, listType);
		for (OrderDTO orderDTO : orders){
			productOrderRepository.save(toEntity(orderDTO));
		}
	}

	public List<OrderDTO> getReadyOrders(Long id) {
		List<ProductOrder> readyOrders = productOrderRepository.findAllByStatusAndMemberId(DeliveryStatus.READY, id);
		List<OrderDTO> orders = new ArrayList<>();
		for (ProductOrder productOrder : readyOrders) {
			orders.add(toDTO(productOrder));
		}
		return orders;
	}

	private ProductOrder toEntity(OrderDTO orderDTO) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime time = LocalDateTime.parse(orderDTO.getTime() + " 00:00:00", formatter);
		ProductOrder productOrder = ProductOrder.builder()
				.orderName(orderDTO.getProductName())
				.buyProductCount(orderDTO.getCount())
				.salePrice(orderDTO.getPrice())
				.totalPrice(orderDTO.getPrice() * orderDTO.getCount())
				.status(DeliveryStatus.READY)
				.build();
		productOrder.setInsertDateTime(time);
		return productOrder;
	}

	private OrderDTO toDTO(ProductOrder productOrder) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return OrderDTO.builder()
				.count(productOrder.getBuyProductCount())
				.price(productOrder.getTotalPrice())
				.status(productOrder.getStatus())
				.productName(productOrder.getOrderName())
				.time(productOrder.getInsertDateTime().format(formatter))
				.build();
	}

}
