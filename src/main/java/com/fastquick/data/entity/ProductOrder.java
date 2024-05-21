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

	@ManyToOne
	@JoinColumn(name = "shopProductId")
	private ShopProduct shopProduct;

	@OneToMany(mappedBy = "shopConnection")
	private List<JoinTableEntity> joinTableEntities = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "memberId")
	private Member member;

	private Long orderId;
	private String orderName;
	private int buyProductCount;
	private int salePrice;

	private int totalPrice;
	private int customId; // 있는 이유 물어보기
	private String phoneNumber;
	private String zipCode;
	private String address;
	private String addressDetail;
	private String customMemo;

}
