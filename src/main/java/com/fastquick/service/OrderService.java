package com.fastquick.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fastquick.data.dto.OrderDTO;
import com.fastquick.data.dto.request.MemberRequestDTO;
import com.fastquick.data.entity.*;
import com.fastquick.data.repository.*;
import com.fastquick.data.util.DeliveryStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
@Transactional(readOnly = true)
public class OrderService {
	private final ProductOrderRepository productOrderRepository;
	private final MemberRepository memberRepository;
	private final ShopConnectionRepository shopConnectionRepository;
	private final ShopProductRepository shopProductRepository;
	private final ProductRepository productRepository;
	private Map<String, String> adapter = new HashMap<>();

	public OrderService(ProductOrderRepository productOrderRepository, MemberRepository memberRepository, ShopConnectionRepository shopConnectionRepository, ShopProductRepository shopProductRepository, ProductRepository productRepository) {
		this.productOrderRepository = productOrderRepository;
		this.memberRepository = memberRepository;
		this.shopConnectionRepository = shopConnectionRepository;
		this.shopProductRepository = shopProductRepository;
		this.productRepository = productRepository;
		siteInit();
	}

	private void siteInit() {
		adapter.put("A", "http://localhost:9080/v2/providers/openapi/apis/api/v4/vendors/ordersheets");
		adapter.put("B", "http://localhost:9090/v2/providers/openapi/apis/api/v4/vendors/ordersheets");
	}

	// Post 형식의 RestTemplate
	@Transactional
	public void postWithParamAndBody() {
		List<Member> members = memberRepository.findAll();
		for (Member member : members) {
			saveOrderByMember(member);
		}
	}

	@Transactional
	public void updateOrderById (int id){
		Optional<Member> member = memberRepository.findById(id);
		member.ifPresent((i) -> saveOrderByMember(member.get()));
	}

	private void saveOrderByMember(Member member){
		List<ShopConnection> shopConnections = shopConnectionRepository.findByMemberId(member.getMemberId());
		for (ShopConnection shopConnection : shopConnections) {
			ShopProduct shopProduct = shopProductRepository.findOneByMemberAndShopConnection(member.getMemberId(), shopConnection.getConnectionId(), shopConnection.getShopId());
			Optional<String> site = getSite(shopConnection.getShopId());
			if (site.isPresent()) {
				List<OrderDTO> orders = getByOrderDTOByAPI(site.get(), shopConnection.getConnectionId());
				for (OrderDTO orderDTO : orders) {
					productOrderRepository.save(ProductOrder.toEntity(orderDTO, member, shopProduct, shopConnection));
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
				.encode()
				.build()
				.toUri();
		OrderDTO requestDTO = new OrderDTO();
		requestDTO.setVendorId(connectionId); // 원래라면 258942 connectionId가 들어가야 한다.
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Map> result = restTemplate.postForEntity(uri, requestDTO, Map.class);
		List<LinkedHashMap> data = (List<LinkedHashMap>) result.getBody().get("data");
		ObjectMapper mapper = new ObjectMapper();
		CollectionType listType = mapper.getTypeFactory().constructCollectionType(List.class, OrderDTO.class);
		List<OrderDTO> orders = mapper.convertValue(data, listType);
		return orders;
	}

	public List<OrderDTO> getReadyOrders(int id) {
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
				.orderId(productOrder.getId())
				.buyProductCount(productOrder.getBuyProductCount())
				.salePrice(productOrder.getTotalPrice())
				.status(productOrder.getStatus())
				.sellerProductName(productOrder.getOrderName())
				.insertDateTime(productOrder.getInsertDateTime().format(formatter))
				.build();
	}

	@Transactional
	public void cancelOrder(int productOrderId){
		Optional<ProductOrder> byId = productOrderRepository.findById(productOrderId);
		ProductOrder productOrder = byId.get();
		ShopProduct shopProduct = shopProductRepository.findById(productOrder.getShopProduct().getShopProductId()).get();

		int productId = productOrder.getShopProduct().getProductId();
		Product product = productRepository.findById(productId).get();

		productOrder.cancel();
		product.addStock(productOrder.getBuyProductCount());
	}

	@Transactional
	public void releaseOrder(int productOrderId){
		ProductOrder productOrder = productOrderRepository.findById(productOrderId).get();
		ShopProduct shopProduct = shopProductRepository.findById(productOrder.getShopProduct().getShopProductId()).get();
		Product product = productRepository.findById(productOrder.getShopProduct().getProductId()).get();

		productOrder.release();
		product.minusStock(productOrder.getBuyProductCount());
	}
}
