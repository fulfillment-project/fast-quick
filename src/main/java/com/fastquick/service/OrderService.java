package com.fastquick.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fastquick.data.dto.OrderDTO;
import com.fastquick.data.dto.request.MemberRequestDTO;
import com.fastquick.data.entity.Member;
import com.fastquick.data.entity.ProductOrder;
import com.fastquick.data.entity.ShopConnection;
import com.fastquick.data.entity.ShopProduct;
import com.fastquick.data.repository.MemberRepository;
import com.fastquick.data.repository.ProductOrderRepository;
import com.fastquick.data.repository.ShopConnectionRepository;
import com.fastquick.data.repository.ShopProductRepository;
import com.fastquick.data.util.DeliveryStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
	private final MemberRepository memberRepository;
	private final ShopConnectionRepository shopConnectionRepository;
	private final ShopProductRepository shopProductRepository;
	private static Map<String, String> adapter = new HashMap<>();

	private void siteInit() {
		adapter.put("A", "http://localhost:8080/api/get/order");
		adapter.put("B", "B사이트 URL");
	}

	// Post 형식의 RestTemplate
	@Transactional
	public void postWithParamAndBody() {
		List<Member> members = memberRepository.findAll();
		List<OrderDTO> orders = null;
		for (Member member : members) {
			List<ShopConnection> shopConnections = shopConnectionRepository.findByMemberId(member.getMemberId());
			for (ShopConnection shopConnection : shopConnections) {
				ShopProduct shopProduct = shopProductRepository.findOneByMemberAndShopConnection(member.getMemberId(), shopConnection.getConnectionId(), shopConnection.getShopId());
				Optional<String> site = getSite(shopConnection.getShopId());
				if (site.isPresent()) {
					orders = getByOrderDTOByAPI(site.get(), shopConnection.getConnectionId());
					for (OrderDTO orderDTO : orders) {
						productOrderRepository.save(ProductOrder.toEntity(orderDTO, member, shopProduct, shopConnection));
					}
				}
			}
		}
	}

	private Optional<String> getSite(String type) {
		return adapter.entrySet().stream()
				.filter(i -> i.getKey().equals(type))
				.map(Map.Entry::getValue)
				.findAny();
	}

	private List<OrderDTO> getByOrderDTOByAPI(String site, int connectionId) {
		URI uri = UriComponentsBuilder
				.fromUriString(site)
				.queryParam("vendorId", connectionId)
				.encode()
				.build()
				.toUri();

		MemberRequestDTO memberRequestDTO = MemberRequestDTO.builder()
				.id("")
				.pwd("")
				.build();

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Map> result = restTemplate.postForEntity(uri, memberRequestDTO, Map.class);
		List<LinkedHashMap> data = (List<LinkedHashMap>) result.getBody().get("data");
		ObjectMapper mapper = new ObjectMapper();
		CollectionType listType = mapper.getTypeFactory().constructCollectionType(List.class, OrderDTO.class);
		return mapper.convertValue(data, listType);
	}

	public List<OrderDTO> getReadyOrders(Long id) {
		List<ProductOrder> readyOrders = productOrderRepository.findAllByStatusAndMemberId(DeliveryStatus.READY, id);
		List<OrderDTO> orders = new ArrayList<>();
		for (ProductOrder productOrder : readyOrders) {
			orders.add(toDTO(productOrder));
		}
		return orders;
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
