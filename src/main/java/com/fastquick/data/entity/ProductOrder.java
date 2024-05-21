package com.fastquick.data.entity;

import lombok.*;

import javax.persistence.*;
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

}
