package com.fastquick.data.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
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
			@JoinColumn(name = "connectionId"),
			@JoinColumn(name = "shopId")
	})
	private ShopConnection shopConnection;

<<<<<<< HEAD
	/*
	 * @OneToMany(mappedBy = "shopConnection") private List<JoinTableEntity>
	 * joinTableEntities = new ArrayList<>();
	 */
	
	@OneToMany(mappedBy = "productOrder")
	private List<JoinTableEntity> joinTableEntities = new ArrayList<>();

	@ManyToOne
=======
	@ManyToOne(fetch = FetchType.LAZY)
>>>>>>> f85427917f7f0b75ee59fff5d327d96bf747bdfd
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
