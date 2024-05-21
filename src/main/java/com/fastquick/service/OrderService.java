package com.fastquick.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fastquick.data.dto.OrderDTO;
import com.fastquick.data.dto.request.MemberRequestDTO;
import com.fastquick.data.repository.ProductOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderService {
	private final ProductOrderRepository productOrderRepository;
	public String getName() {
		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:8080")
				.path("/api/get/order")
				.encode()
				.build()
				.toUri();
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
		System.out.println("test");
		return responseEntity.getBody();
	}

	// Post 형식의 RestTemplate
	public List<OrderDTO> postWithParamAndBody() {
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



		return mapper.convertValue(data, listType);
	}
}
