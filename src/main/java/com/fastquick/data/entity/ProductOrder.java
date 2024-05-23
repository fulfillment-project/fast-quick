package com.fastquick.data.entity;

import com.fastquick.data.dto.OrderDTO;
import com.fastquick.data.util.DeliveryStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "productOrder")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ProductOrder extends BaseEntity {
	@Id
	@GeneratedValue
	@Column(name = "productOrderId")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "shopProductId")
	private ShopProduct shopProduct;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "shopId"),
			@JoinColumn(name = "connectionId")
	})
	private ShopConnection shopConnection;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "memberId")
	private Member member;

	private Long orderId;
	private String orderName;
	private int buyProductCount;
	private int salePrice;

	private int totalPrice;
	private int customId; // 주문자 이름
	private String phoneNumber;
	private String zipCode;
	private String address;
	private String addressDetail;
	private String customMemo;

	@Enumerated(EnumType.STRING)
	private DeliveryStatus status;

	public static ProductOrder toEntity(OrderDTO orderDTO, Member member, ShopProduct shopProduct, ShopConnection shopConnection) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime time = LocalDateTime.parse(orderDTO.getInsertDateTime() + " 00:00:00", formatter);
		ProductOrder productOrder = ProductOrder.builder()
				.member(member)
				.shopProduct(shopProduct)
				.shopConnection(shopConnection) // 관련 엔티티 설정
				.orderName(orderDTO.getSellerProductName())
				.buyProductCount(orderDTO.getBuyProductCount())
				.salePrice(orderDTO.getSalePrice())
				.totalPrice(orderDTO.getTotalPrice())
				.status(DeliveryStatus.READY)
				.address(orderDTO.getAddress())
				.customId(orderDTO.getCustomId())
				.customMemo(orderDTO.getMemo())
				.phoneNumber(orderDTO.getPhoneNumber())
				.build();
		productOrder.setInsertDateTime(time);
		return productOrder;
	}

	/**
	 * 주문 취소
	 */
	public void cancel() {
		if (status == DeliveryStatus.COMP) {
			throw new IllegalStateException("이미 배송완료되어 취소 불가능합니다.");
		}
		this.setStatus(DeliveryStatus.CANCEL);
		shopProduct.addStock(buyProductCount);
	}

	public void release() {
		shopProduct.minusStock(buyProductCount);
	}

}
